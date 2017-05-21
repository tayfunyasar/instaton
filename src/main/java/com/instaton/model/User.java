package com.instaton.model;

import javax.persistence.*;

import org.apache.commons.lang3.StringUtils;

import com.instaton.utils.SocialSignInProvider;
import com.instaton.utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1492143209352515667L;

	@Id
	@GeneratedValue
	private Long id;

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private UserRole userRole = UserRole.User;

	private String socialImageUrl;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<UserConnection> userConnections = new ArrayList<>();

	public String getBookmarkableUrl() {
		return String.format("/user/%s/%s", getId().toString(), UrlUtils.makeBookmarkable(getFullName()));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		String delimiter = " ";
		if (StringUtils.isBlank(firstName) || StringUtils.isBlank(lastName)) {
			delimiter = "";
		}
		return String.join(delimiter, StringUtils.defaultString(firstName), StringUtils.defaultString(lastName));
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<UserConnection> getUserConnections() {
		return userConnections;
	}

	public void setUserConnections(List<UserConnection> userConnections) {
		this.userConnections = userConnections;
	}

	public void addUserConnection(UserConnection userConnection) {
		this.userConnections.add(userConnection);
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public String getSocialImageUrl() {
		return socialImageUrl;
	}

	public void setSocialImageUrl(String socialImageUrl) {
		this.socialImageUrl = socialImageUrl;
	}

	public boolean hasSocialImageUrl() {
		return StringUtils.isNotBlank(socialImageUrl);
	}

	public boolean hasTwitterConnection() {
		return getTwitterConnection() != null;
	}

	public UserConnection getTwitterConnection() {
		return getSocialConnection(SocialSignInProvider.Twitter);
	}

	private UserConnection getSocialConnection(SocialSignInProvider provider) {
		return userConnections.stream()
				.filter(uc -> provider == SocialSignInProvider.getForProviderId(uc.getProviderId()).orElse(null))
				.findFirst().orElse(null);
	}

	public boolean isAdmin() {
		return UserRole.Admin == userRole;
	}

	public boolean isNotAdmin() {
		return !isAdmin();
	}

}