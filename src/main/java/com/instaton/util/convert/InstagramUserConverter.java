package com.instaton.util.convert;

import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;

import com.instaton.entity.social.instagram.InstagramUserEntity;

public class InstagramUserConverter {

  public static InstagramUserEntity convert(final InstagramUser p) {
    final InstagramUserEntity user = new InstagramUserEntity();

    user.setAddressStreet(p.getAddress_street());
    user.setBiography(p.getBiography());
    user.setBusinessContactMethod(p.getBusiness_contact_method());
    user.setCityName(p.getCity_name());
    user.setDirectMessaging(p.getDirect_messaging());
    user.setExternalLynxUrl(p.getExternal_lynx_url());
    user.setExternalUrl(p.getExternal_url());
    user.setFavorite(p.is_favorite());
    user.setBusiness(p.is_business());
    user.setFollowerCount(p.getFollower_count());
    user.setFollowingCount(p.getFollowing_count());
    user.setFullName(p.getFull_name());
    user.setGeoMediaCount(p.getGeo_media_count());
    user.setHasAnonymousProfilePicture(p.isHas_anonymous_profile_picture());
    user.setHasBiographyTranslation(p.isHas_biography_translation());
    //    user.setHdProfilePicUrl(p.getHd_profile_pic_url_info().getUrl());
    user.setLatitude(p.getLatitude());
    user.setLongitude(p.getLongitude());
    user.setMediaCount(p.getMedia_count());
    user.setPk(p.getPk());
    user.setPrivate(p.is_private());
    //    user.setPrivateEmail(p.getPublic_email());
    //    user.setPrivatePhoneCountryCode(p.phone);
    user.setProfilePicId(p.getProfile_pic_id());
    user.setUsername(p.getUsername());
    user.setProfilePicUrl(p.getProfile_pic_url());
    user.setUseTagsCount(p.getUsertags_count());
    user.setVerified(p.is_verified());
    user.setZip(p.getZip());

    return user;
  }

  private InstagramUserConverter() {
    throw new IllegalAccessError();
  }
}
