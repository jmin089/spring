package com.java.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class KakaoDto {

	public Long id;
	public String connected_at;
	public Properties properties;       //객체이기에 class 생성
	public Kakao_account kakao_account; //객체이기에 class 생성
	
	@Data
	public class Properties{
		public String nickname;
		public String profile_image;
		public String thumbnail_image;
	}
	
	@Data
	public class Kakao_account{
		public Boolean profile_nickname_needs_agreement;
		public Boolean profile_image_needs_agreement;
		public Profile profile;
		//생략가능 - 설정을 안했음.
		public String name;
		public Boolean name_needs_agreement;
		public String email;
		public Boolean email_needs_agreement;
		public String birthday;
		public Boolean birthday_needs_agreement;
		public String gender;
		public Boolean gender_needs_agreement;
		public String phone_number;
		public Boolean phone_number_needs_agreement;
		
		@Data
		public class Profile{
			public String nickname;
			public String thumbnail_image_url;
			public String profile_image_url;
			public Boolean is_default_image;
		}
	}
}
