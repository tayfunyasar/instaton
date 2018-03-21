package com.instaton.entity.social.instagram;

import java.util.List;
import java.util.Map;

import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedUserTag;

public class InstagramFeedItemEntity {

  private InstagramUserEntity user;
  //  private List<InstagramUserEntity> likers;

  private long takenAtt;
  private long pk;
  private String idInstagramfeeditem;
  private long deviceTimestamp;
  private int mediaType;
  private String code;
  private String clientCacheKey;
  private int filterType;
  private boolean hasAudio;
  private double videoDuration;
  private Map<String, Object> attribution;
  private List<Object> videoVersions;
  private Map<String, Object> imageVersions2;
  private List<InstagramFeedUserTag> usertags;
  private Map<String, Object> location;
  private float lng;
  private float lat;
  private int originalWidth;
  private int originalHeight;
  private int viewCount;

  private String organicTrackingToken;
  private int likeCount;
  private List<String> topLikers;
  private boolean hasliked;
  private boolean commentLikesEnabled;
  private boolean hasMoreComments;
  private long nextMaxId;
  private int maxNumVisiblePreviewComments;
  private List<Object> previewComments;
  private List<Object> comments;
  private boolean commentCount;
  private Map<String, Object> caption;

  private boolean captionIsEdited;
  private boolean photoOfYou;
  private boolean commentsDisabled;
}
