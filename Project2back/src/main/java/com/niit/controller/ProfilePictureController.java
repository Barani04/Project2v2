package com.niit.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.dao.ProfilePictureDao;
import com.niit.dao.UserDao;
import com.niit.model.ProfilePicture;
import com.niit.model.User;
import com.niit.service.Error;


@Controller
public class ProfilePictureController {
	@Autowired
private ProfilePictureDao profilePictureDao;
	
	@Autowired
	private UserDao userdao;
	
	@RequestMapping(value="/uploadprofilepic",method=RequestMethod.POST)
public ResponseEntity<?> uploadProfilePicture(@RequestParam CommonsMultipartFile image,HttpSession session){
	String username=(String) session.getAttribute("username");
	if(username==null)		{
		    Error error=new Error(3,"UnAuthorized user");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	} 
	ProfilePicture profilePicture=new ProfilePicture();
	User user = userdao.getUserByUsername(username);
	profilePicture.setUsername(username);
	profilePicture.setImage(image.getBytes());
	profilePictureDao.saveProfilePicture(profilePicture);
	return new ResponseEntity<User>(user,HttpStatus.OK);
}
	
	//http://localhost:8082/backend_project2/getimage/admin
	@RequestMapping(value="/getimage/{username}", method=RequestMethod.GET)
	public @ResponseBody byte[] getProfilePic(@PathVariable String username,HttpSession session){
		String userName = (String) session.getAttribute("username");
		
		User user= userdao.getUserByUsername(userName);
		if(user==null)
			return null;
		else
		{
			ProfilePicture profilePic=profilePictureDao.getProfilePicture(username);
			if(profilePic==null)
				return null;
			else
				return profilePic.getImage();
		}
		
}
	
	
	

}
