package com.java.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class KakaoDto {
	
	public long id;
	public String connected_at;
	public Properties properties;
	public Kakao_account kakao_account;
	
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
		
		@Data
		public class Profile{
			public String nickname;
			public String thumbnail_image_url;
			public String profile_image_url;
			public Boolean is_default_image;
		}
	}

}
