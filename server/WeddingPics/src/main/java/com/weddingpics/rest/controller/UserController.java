package com.weddingpics.rest.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.google.gson.Gson;
import com.weddingpics.rest.entity.Album;
import com.weddingpics.rest.entity.User;
import com.weddingpics.rest.model.ClientResponseObject;
import com.weddingpics.rest.model.Picture;
import com.weddingpics.rest.service.AlbumService;
import com.weddingpics.rest.service.PictureService;
import com.weddingpics.rest.service.UserService;
import com.weddingpics.rest.utills.EmailValidator;
import com.weddingpics.rest.utills.EntityToVOConverter;
import com.weddingpics.rest.utills.ImageTypeEnum;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AlbumService albumService;
	
	@Autowired
	private PictureService pictureService;
	
	public static final EmailValidator emailValidator = new EmailValidator();
	
	/**
	 * This method check if user exist or not than crate new user and create album for that user.
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional
	@RequestMapping(value="/uc/createAlbum", method = RequestMethod.POST ,produces = "application/json")
	 public @ResponseBody String addUser(Model model, HttpServletRequest request) throws Exception {
 
		ClientResponseObject response = new ClientResponseObject(); 
		Gson gson = new Gson();
		
		String userEmail = StringUtils.isNotBlank(request.getParameter("email"))?request.getParameter("email"):null;
		String userName = StringUtils.isNotBlank(request.getParameter("fullName"))?request.getParameter("fullName"):null;
		String password = StringUtils.isNotBlank(request.getParameter("password"))?request.getParameter("password"):null;
		Boolean isNewUser = StringUtils.isNotBlank(request.getParameter("isNewUser"))?Boolean.parseBoolean(request.getParameter("isNewUser")):null;
		
		Boolean isValidUser = false;
		User user = null;
		if (StringUtils.isNotBlank(userEmail)&& StringUtils.isNotBlank(password)) {
			if (emailValidator.validate(userEmail)) {
				Md5PasswordEncoder encoder = new Md5PasswordEncoder();
				if (isNewUser) {
						if (StringUtils.isNotBlank(userName)) {
							user = new User();
							user.setEmailId(userEmail);
							user.setFullName(userName);
							String uuid = UUID.randomUUID().toString();
							user.setPassword(encoder.encodePassword(password,null));
							user.setToken(uuid);
							user.setModifyDttm(new Date());
							User u = userService.findUserByEmailId(userEmail);
							if (u == null) {
								isValidUser = true;
							} else {
								response.setErrorMessage("Email id already in database try another email id");
								response.setIsSuccess(false);
							}
					  }  else if (StringUtils.isBlank(userName)) {
						  response.setErrorMessage("User name not blank");
						  response.setIsSuccess(false);
						} 
				}  else {
					user = userService.findUser(userEmail, encoder.encodePassword(password,null));
					if (user != null) {
						isValidUser = true;
					} else {
						response.setErrorMessage("User not an existing user");
						response.setIsSuccess(false);
					}
				}
			} else {
				response.setErrorMessage("User email id not right");
				response.setIsSuccess(false);
			}
		} else if (StringUtils.isBlank(userEmail)) {
			response.setErrorMessage("User email not blank");
		} else if (StringUtils.isBlank(password)) {
			response.setErrorMessage("Password not blank");
			response.setIsSuccess(false);
		}
		
		if (isValidUser) {
			
			String weddingId = StringUtils.isNotBlank(request.getParameter("weddingId"))?request.getParameter("weddingId"):null;
			String weddingdate = StringUtils.isNotBlank(request.getParameter("weddingdate"))?request.getParameter("weddingdate"):null;
			String firstUser = StringUtils.isNotBlank(request.getParameter("firstUser"))?request.getParameter("firstUser"):null;
			Integer firstUserType = StringUtils.isNotBlank(request.getParameter("firstUserType"))?Integer.parseInt(request.getParameter("firstUserType")):null;
			String secondUser = StringUtils.isNotBlank(request.getParameter("secondUser"))?request.getParameter("secondUser"):null;
			Integer secondUserType = StringUtils.isNotBlank(request.getParameter("secondUserType"))?Integer.parseInt(request.getParameter("secondUserType")):null;
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Album album = albumService.findAlbumByWeddingId(weddingId);
			if (album == null) {
				if (isNewUser) {
					userService.addUser(user);
				}
				response.setUser(EntityToVOConverter.convertToVO(user)); //Set User information in response object.  
				album = new Album(); 
				album.setUser(user);
				album.setFirstUser(firstUser);
				album.setFirstUserType(firstUserType);
				album.setSecondUser(secondUser);
				album.setSecondUserType(secondUserType);
				album.setWeddingdate(dateFormat.parse(weddingdate));
				album.setWeddingId(weddingId);
				album.setModifyDttm(new Date());
				albumService.addAlbum(album);
				response.setAlbum(EntityToVOConverter.convertToVO(album)); //Set Album information in response object.  
				response.setIsSuccess(true);
			} else {
				response.setErrorMessage("Wedding Id already taken please change your wedding id.");
				response.setIsSuccess(false);
			}
		}
		
		return gson.toJson(response);
	}
	
	/**
	 * This method check if user exist than send user unique token identification.   
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional
	@RequestMapping(value="/uc/getUser", method = RequestMethod.POST ,produces = "application/json")
	 public @ResponseBody String getUser(Model model, HttpServletRequest request) throws Exception {
		
		ClientResponseObject response = new ClientResponseObject(); 
		Gson gson = new Gson();
		
		String userEmail = StringUtils.isNotBlank(request.getParameter("userEmail"))?request.getParameter("userEmail"):null;
		String password = StringUtils.isNotBlank(request.getParameter("password"))?request.getParameter("password"):null;
		
		if (StringUtils.isNotBlank(userEmail)&& StringUtils.isNotBlank(password)) {
			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			if (emailValidator.validate(userEmail)) {
				User user = userService.findUser(userEmail,encoder.encodePassword(password, null));
				if (user != null) {
					com.weddingpics.rest.model.User userModle = new com.weddingpics.rest.model.User();
					userModle.setToken(user.getToken());
					response.setUser(userModle);
					response.setIsSuccess(true);
				} else {
					response.setErrorMessage("User not exist in our databse");
					response.setIsSuccess(false);
				}
			} else {
				response.setErrorMessage("User email id not right");
				response.setIsSuccess(false);
			}
			
		} else if (StringUtils.isBlank(userEmail)) {
			response.setErrorMessage("User email not blank");
			response.setIsSuccess(false);
		} else if (StringUtils.isBlank(password)) {
			response.setErrorMessage("Password not blank");
			response.setIsSuccess(false);
		}
		return gson.toJson(response);
	}
	
	/**
	 * Using Wedding Id we get album and send album and its associated pictures model and user name who click that picture.
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional
	@RequestMapping(value="/uc/getAlbum", method = RequestMethod.POST ,produces = "application/json")
	 public @ResponseBody String getAlbum(Model model, HttpServletRequest request) throws Exception {
		
		ClientResponseObject response = new ClientResponseObject(); 
		Gson gson = new Gson();
		
		String weddingId = StringUtils.isNotBlank(request.getParameter("weddingId"))?request.getParameter("weddingId"):null;
		if (StringUtils.isNotBlank(weddingId)) {
				Album album = albumService.findAlbumByWeddingId(weddingId);
				if (album != null) {
					response.setAlbum(EntityToVOConverter.convertToVO(album));
					if (CollectionUtils.isNotEmpty(album.getPictures())) {
						List<Picture> pictures = new ArrayList<Picture>();
						response.setPictures(pictures);
						for (com.weddingpics.rest.entity.Picture picture :album.getPictures()) {
							Picture pictureModel = EntityToVOConverter.convertToVO(picture);
							com.weddingpics.rest.model.User user = new com.weddingpics.rest.model.User();
							user.setFullName(picture.getUser().getFullName());
							pictureModel.setUser(user);
							pictures.add(pictureModel);
						}
					}
					response.setIsSuccess(true);
				} else {
					response.setErrorMessage("Album not exist in our databse");
					response.setIsSuccess(false);
				}
		} else if (StringUtils.isBlank(weddingId)) {
			response.setErrorMessage("WeddingId not blank");
			response.setIsSuccess(false);
		} 
		return gson.toJson(response);
	}
	
	
	/**
	 * Using that method we can upload image in server and that url save in our database for further access.    
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional
	@RequestMapping(value="/uc/saveImage", method = RequestMethod.POST ,produces = "application/json")
	 public @ResponseBody String saveImage(Model model, HttpServletRequest request) throws Exception {
		
		ClientResponseObject response = new ClientResponseObject(); 
		Gson gson = new Gson();
		
		String imageDataString = StringUtils.isNotBlank(request.getParameter("image"))?request.getParameter("image"):null;
		Long albumId = StringUtils.isNotBlank(request.getParameter("albumId"))?Long.parseLong(request.getParameter("albumId")):null;
		Integer imageType = StringUtils.isNotBlank(request.getParameter("imageType"))?Integer.parseInt(request.getParameter("imageType")):null;
		Long userId = StringUtils.isNotBlank(request.getParameter("userId"))?Long.parseLong(request.getParameter("userId")):null;
		String imageDesc = StringUtils.isNotBlank(request.getParameter("imageDesc"))?request.getParameter("imageDesc"):null;
		
		
		if (StringUtils.isNotBlank(imageDataString) && albumId != null && imageType != null && userId != null ) {
			
				Album album = albumService.findAlbumById(albumId);
				User user = userService.findUserById(userId);
				// Converting a Base64 String into Image byte array
	            byte[] imageByteArray = decodeImage(imageDataString);
	             
	           String imgatePath = "";
	           String albumPath =  "images/albums/"+album.getAlbumId();;
	            if (imageType.equals(ImageTypeEnum.WEDDING.getValue())) {
	            	  SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
	            	  imgatePath = "images/albums/"+album.getAlbumId()+"/"+dateFormat.format(new Date())+".jpg";
	            } else if (imageType.equals(ImageTypeEnum.COVER.getValue())) {
	            	 imgatePath = "images/albums/"+album.getAlbumId()+"/cover_image.jpg";
	            }
	           
	            ServletContext context = RequestContextUtils.getWebApplicationContext(request).getServletContext();
	            String imageFullPath = context.getRealPath(imgatePath);
	            String albumFullPath = context.getRealPath(albumPath);
	            // Write a image byte array into file system
	            File file = new File(albumFullPath);
	            if (!file.exists()) {
	            	file.mkdir();
	            }
	        	InputStream in = new ByteArrayInputStream(imageByteArray);
				BufferedImage bImageFromConvert = ImageIO.read(in);
				ImageIO.write(bImageFromConvert, "jpg", new File(imageFullPath));
	            
				if (album != null && user != null) {
					if (!imageType.equals(ImageTypeEnum.COVER.getValue())) {
						com.weddingpics.rest.entity.Picture picture = new com.weddingpics.rest.entity.Picture();
						picture.setUrl("http://10.0.2.2:8080/weddingpics/"+imgatePath);
						picture.setPictureDate(new Date());
						picture.setPictureTitle(imageDesc);
						picture.setImageType(imageType);
						picture.setAlbum(album);
						picture.setUser(user);
						pictureService.addPicture(picture);
					} else {
						album.setCoverImage("http://10.0.2.2:8080/weddingpics/"+imgatePath);
						albumService.UpdateAlbum(album);
					}
					response.setIsSuccess(true);
				} else if (album == null) {
					response.setErrorMessage("Album not exist in our databse");
					response.setIsSuccess(false);
				} else if (user == null) {
					response.setErrorMessage("Album not exist in our databse");
					response.setIsSuccess(false);
				}
			
			
		} else if (StringUtils.isBlank(imageDataString)) {
			response.setErrorMessage("image data not blank");
			response.setIsSuccess(false);
		} else if (albumId == null) {
			response.setErrorMessage("albumId not blank");
			response.setIsSuccess(false);
		} else if (imageType == null) {
			response.setErrorMessage("imageType not blank");
			response.setIsSuccess(false);
		} else if (userId == null) {
			response.setErrorMessage("userId not blank");
			response.setIsSuccess(false);
		} 
		
		return gson.toJson(response);
	}
	
     
    /**
     * Decodes the base64 string into byte array
     *
     * @param imageDataString - a {@link java.lang.String}
     * @return byte array
     */
    public static byte[] decodeImage(String imageDataString) {
        return Base64.decodeBase64(imageDataString.getBytes());
    }
 
	
}


