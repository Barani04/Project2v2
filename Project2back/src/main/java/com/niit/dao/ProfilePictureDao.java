package com.niit.dao;

import com.niit.model.ProfilePicture;

public interface ProfilePictureDao {
	void saveProfilePicture(ProfilePicture proPic );
	ProfilePicture getProfilePic(String username);
}
