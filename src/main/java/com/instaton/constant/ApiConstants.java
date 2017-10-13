package com.instaton.constant;

public class ApiConstants {

	private ApiConstants() {}

	public static final String SSO_LOGIN_LOGOUT = "/partner/login/logout.json";

	public static final String SSO_LOGIN_VERIFY = "/partner/login/ssoLoginVerify.json";

	public static final String DETAILED_GMS_BREAKDOWN_BASED_ON_CARD = "/partner/report/getDetailedGmsBreakdownBasedOnCard.json";

	public static final String DETAILED_GMS_BREAKDOWN_BASED_ON_MERCHANT = "/partner/report/getDetailedGmsBreakdownBasedOnMerchant.json";

	public static final String PARAMETER_LIST = "/partner/generic/getParameterList.json";

	public static final String CAMPAIGN_LIST_AFTER_LOGIN = "/partner/campaign/getCampaignListAfterLogin.json";

	public static final String SESSION_OPERATION = "/partner/login/sessionOperation.json";

	public static final String COLLECTIVE_CMS_INFO = "/partner/generic/getCollectiveCMSInfo.json";

	public static final String FIRM_DETAIL = "/partner/generic/getFirmDetail.json";

	// merchant
	public static final String FIRM_MERCHANT_LIST = "/partner/generic/getFirmMerchantList.json";

	public static final String FIRM_MERCHANT_GROUP_LIST = "/partner/generic/getFirmMerchantGroupList.json";

	public static final String MERCHANT_GROUP_OPERATION = "/partner/generic/merchantGroupOperation.json";

	public static final String MERCHANT_GROUP_LIST = "/partner/generic/getMerchantGroupList.json";

	public static final String FIRM_STATEMENT_DETAIL = "/partner/generic/firmStatementDetail.json";

	// notification
	public static final String NOTIFICATION_LIST = "/partner/generic/getNotificationList.json";

	public static final String NOTIFICATION_OPERATION = "/partner/generic/notificationOperation.json";

	public static final String SEND_EMAIL = "/partner/generic/sendEmail.json";

	// summary

	public static final String SUMMARY_REPORT_DEFINITION = "/partner/generic/summaryReportDefinition.json";

	public static final String SUMMARY_REPORT_LIST = "/partner/generic/summaryReportList.json";

	// templates

	public static final String TEMPLATE_DEFINATION = "/partner/generic/templateDefinition.json";

	public static final String TEMPLATE_DETAIL = "/partner/generic/templateDetail.json";

	// report

	public static final String AGGREGATE_GMS_GROWTH_BY_YEAR = "/partner/report/getAggregateGMSGrowthByYears.json"; // ok

	public static final String GMS_GROWTH = "/partner/report/getGMSGrowth.json"; // ok

	public static final String POS_CASH_FLOW = "/partner/report/getPosCashFlow.json"; // ok

	public static final String CITY_AND_STORE_GMS_DISTRIBUTION = "/partner/report/getCityAndStoreGmsDistribution.json"; // ok

	public static final String BASKET_AVERAGE = "/partner/report/getBasketAverage.json";

	public static final String SEKTOR_AND_FIRM_GROWTH_RATE = "/partner/report/getSectorAndFirmGrowthRate.json";

	public static final String INSTALLMENT_BASED_DETAILED_GMS_GROWTH = "/partner/report/getInstallmentBasedDetailedGMSGrowth.json";

	public static final String TXN_ARRIVAL_FREQUENCY = "/partner/report/getTxnArrivalFrequency.json";

	public static final String CUSTOMER_TENDENCY_TO_SPEND = "/partner/report/getCustomerTendencyToSpend.json";

	// campaign
	public static final String CAMPAIGN_LIST = "/partner/campaign/getCampaignList.json"; // ok

	// PORTAL
	public static final String CAMPAING_CMS_URL = "/o/bonusapi/content/campaigncontents";

}
