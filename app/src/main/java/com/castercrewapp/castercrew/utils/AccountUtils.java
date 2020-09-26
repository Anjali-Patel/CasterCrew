package com.castercrewapp.castercrew.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import java.util.Set;

/**
 * Created by Yugandar Aaleswara on 0019 19-January-2018.
 **/

public class AccountUtils {
    public static  String VALIDITY="30";
    public static  int AMMOUNT=3900;
    public static String MEMBERSHIP="Silver";

    private static final String TAG = AccountUtils.class.getSimpleName();
    public static final String GOOGLE_EMAIL = "google_email";
    public static final String FCM_TOKEN = "fcm_token";
    public static final String GOOGLE_NAME = "google_name";
    public static final String GOOGLE_PHOTO = "google_photo";
    private static final String PREF_SHOPNAME = "shop_name";
    public static final String PREF_USER_ID = "user_id";
    public  static final String PREF_NAME = "name";
    public static final String PREF_EMAIL = "email";
    private static final String PREF_PHONE = "phone";
    public static final String PREF_PHOTO_URL = "photo_url";
    private static final String PREF_TOKEN = "token";
    private static final String PREF_IS_TOKEN_UPLOAD = "is_token_upload";
    private static final String PREF_ROLE ="role";
    private static final String PREF_LOCATION = "loc";
    private static final String PREF_PASSWORD = "password";

    private static final String PREF_CART_COUNT = "count";
    private static final String PREF_AREA_ID = "area_id";
    private static final String PREF_AREA_NAME = "area_name";
    private static final String PREF_CITY_ID = "city_id";
    private static final String PREF_CITY_NAME = "city_name";
    private static final String PREF_BANNERS = "banner";
    private static final String PREF_DELIVERYC = "delivery_charge";
    private static final String PREF_DISCOUNT ="discount_percentage";
    private static final String PREF_DISCOUNTA ="discount_order_value";
    private static final String PREF_STORE_PHONE="store_phone_number";
    private static final String PREF_MINIMUM_DELIVERY="min_delivery_price";
    private static final String PREF_STIME="store_timings1";
    private static final String PREF_STIME2="store_timings2";
    private static final String PREF_STORE_IMG ="store_img";
    private static final String PREF_M_NAME ="managername";
    private static final String PREF_CATEGORY_ID = "id";
    


    // File Preferences
    private static SharedPreferences getSharedPreferences(final Context context) {
        return context.getSharedPreferences("CasterCrew", Context.MODE_PRIVATE);
    }

    // Settings Preferences
    private static SharedPreferences getDefaultSharedPreferences(final Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Specify whether the app has an active account set.
     *
     * @param context Context used to lookup {@link SharedPreferences} the value is stored with.
     */
    public static boolean hasActiveAccount(final Context context) {
        return !TextUtils.isEmpty(getName(context));
    }

    /**
     * Sets the userID of the active user.
     *
     * @param context     Context used to lookup {@link SharedPreferences}
     * @param userID The userID of the user.
     */

    public static void setshopname(Context context, String userID){
        SharedPreferences sharedPref = getSharedPreferences(context);
        sharedPref.edit().putString(PREF_SHOPNAME, userID).apply();
    }

    /**
     * Returns the userID of active user.
     *
     * @param context Context used to lookup {@link SharedPreferences} the value is stored with.
     */
    public static String getshopname(Context context){
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getString(PREF_SHOPNAME, "");
    }

    public static void setUserID(Context context, int userID){
        SharedPreferences sharedPref = getSharedPreferences(context);
        sharedPref.edit().putInt(PREF_USER_ID, userID).apply();
    }

    /**
     * Returns the userID of active user.
     *
     * @param context Context used to lookup {@link SharedPreferences} the value is stored with.
     */
    public static int getUserID(Context context){
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getInt(PREF_USER_ID, 0);
    }

    /**
     * Sets the name of the active user.
     *
     * @param context     Context used to lookup {@link SharedPreferences}
     * @param name The name of the user.
     */
    public static void setName(Context context, String name){
        SharedPreferences sharedPref = getSharedPreferences(context);
        sharedPref.edit().putString(PREF_NAME, name).apply();
    }

    /**
     * Returns the name of active user.
     *
     * @param context Context used to lookup {@link SharedPreferences} the value is stored with.
     */
    public static String getName(Context context){
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getString(PREF_NAME, "");
    }

    /**
     * Sets the email of the active user.
     *
     * @param context     Context used to lookup {@link SharedPreferences}
     * @param email The email of the user.
     */
    public static void setEmail(Context context, String email){
        SharedPreferences sharedPref = getSharedPreferences(context);
        sharedPref.edit().putString(PREF_EMAIL, email).apply();
    }

    /**
     * Returns the email of active user.
     *
     * @param context Context used to lookup {@link SharedPreferences} the value is stored with.
     */
    public static String getStoretime1(Context context){
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getString(PREF_STIME, "");
    }



    public static void setStoretime1(Context context, String storetime1){
        SharedPreferences sharedPref = getSharedPreferences(context);
        sharedPref.edit().putString(PREF_STIME, storetime1).apply();
    }

    public static String getPrefRole(Context context){
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getString(PREF_ROLE, "");
    }



    public static void setPrefRole(Context context, String storetime1){
        SharedPreferences sharedPref = getSharedPreferences(context);
        sharedPref.edit().putString(PREF_ROLE, storetime1).apply();
    }

    public static String getPrefLocation(Context context){
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getString(PREF_LOCATION, "");
    }



    public static void setPrefLocation(Context context, String storetime1){
        SharedPreferences sharedPref = getSharedPreferences(context);
        sharedPref.edit().putString(PREF_LOCATION, storetime1).apply();
    }



    public static String getStoretime2(Context context){
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getString(PREF_STIME2, "");
    }



    public static void setPrefCategoryId(Context context, String storetime2){
        SharedPreferences sharedPref = getSharedPreferences(context);
        sharedPref.edit().putString(PREF_CATEGORY_ID, storetime2).apply();
    }

    public static String getPrefCategoryId(Context context){
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getString(PREF_CATEGORY_ID, "");
    }

    public static void setPrefPassword(Context context, String storetime2){
        SharedPreferences sharedPref = getSharedPreferences(context);
        sharedPref.edit().putString(PREF_PASSWORD, storetime2).apply();
    }

    public static String getPrefPassword(Context context){
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getString(PREF_PASSWORD, "");
    }



    public static void setStoretime2(Context context, String storetime2){
        SharedPreferences sharedPref = getSharedPreferences(context);
        sharedPref.edit().putString(PREF_STIME2, storetime2).apply();
    }

    /**
     * Returns the email of active user.
     *
     * @param context Context used to lookup {@link SharedPreferences} the value is stored with.
     */
    public static String getEmail(Context context){
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getString(PREF_EMAIL, "");
    }

    /**
     * Sets the phone of the active user.
     *
     * @param context     Context used to lookup {@link SharedPreferences}
     * @param phone The phone of the user.
     */
    public static void setPhone(Context context, String phone){
        SharedPreferences sharedPref = getSharedPreferences(context);
        sharedPref.edit().putString(PREF_PHONE, phone).apply();

    }

    /**
     * Returns the phone of active user.
     *
     * @param context Context used to lookup {@link SharedPreferences} the value is stored with.
     */
    public static String getPhone(Context context){
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getString(PREF_PHONE, "");
    }

    /**
     * Sets the photo URL of the active user.
     *
     * @param context     Context used to lookup {@link SharedPreferences}
     * @param photoUrl The Date of Joining of the user.
     */
    public static void setPhotoUrl(Context context, String photoUrl){
        SharedPreferences sharedPref = getSharedPreferences(context);
        sharedPref.edit().putString(PREF_PHOTO_URL, photoUrl).apply();
    }

    /**
     * Returns the photoUrl of active user.
     *
     * @param context Context used to lookup {@link SharedPreferences} the value is stored with.
     */
    public static String getPhotoUrl(Context context){
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getString(PREF_PHOTO_URL, "");
    }

    /**
     * Sets the device token.
     *
     * @param context     Context used to lookup {@link SharedPreferences}
     * @param token The Date of Joining of the user.
     */
    public static void setToken(Context context, String token){
        SharedPreferences sharedPref = getSharedPreferences(context);
        sharedPref.edit().putString(PREF_TOKEN, token).apply();
        setTokenStatus(context, false);
    }

    /**
     * Returns the token.
     *
     * @param context Context used to lookup {@link SharedPreferences} the value is stored with.
     */
    public static String getToken(Context context){
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getString(PREF_TOKEN, "");
    }


    /**
     * Sets the token upload status of active user.
     *
     * @param context     Context used to lookup {@link SharedPreferences}
     * @param tokenUpStatus of the device.
     */
    public static void setTokenStatus(Context context, boolean tokenUpStatus){
        SharedPreferences sharedPref = getSharedPreferences(context);
        sharedPref.edit().putBoolean(PREF_IS_TOKEN_UPLOAD, tokenUpStatus).apply();
    }

    /**
     * Returns the token upload status of the device.
     *
     * @param context Context used to lookup {@link SharedPreferences} the value is stored with.
     */
    public static boolean hasUploadToken(Context context){
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getBoolean(PREF_IS_TOKEN_UPLOAD, false);
    }

    /**
     * Sets the areaID of the active user.
     *
     * @param context     Context used to lookup {@link SharedPreferences}
     * @param areaID The areaID of the user.
     */
    public static void setAreaID(Context context, int areaID){
        SharedPreferences sharedPref = getSharedPreferences(context);
        sharedPref.edit().putInt(PREF_AREA_ID, areaID).apply();
    }

    /**
     * Returns the areaID of active user.
     *
     * @param context Context used to lookup {@link SharedPreferences} the value is stored with.
     */
    public static int getAreaID(Context context){
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getInt(PREF_AREA_ID, 0);
    }

    /**
     * Sets the areaName of the active user.
     *
     * @param context     Context used to lookup {@link SharedPreferences}
     * @param areaName The areaName of the user.
     */
    public static void setAreaName(Context context, String areaName){
        SharedPreferences sharedPref = getSharedPreferences(context);
        sharedPref.edit().putString(PREF_AREA_NAME, areaName).apply();
    }

    /**
     * Returns the areaName of active user.
     *
     * @param context Context used to lookup {@link SharedPreferences} the value is stored with.
     */
    public static String getAreaName(Context context){
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getString(PREF_AREA_NAME, "");
    }

    /**
     * Sets the cityID of the active user.
     *
     * @param context     Context used to lookup {@link SharedPreferences}
     * @param cityID The cityID of the user.
     */
    public static void setCityID(Context context, int cityID){
        SharedPreferences sharedPref = getSharedPreferences(context);
        sharedPref.edit().putInt(PREF_CITY_ID, cityID).apply();
    }

    /**
     * Returns the cityID of active user.
     *
     * @param context Context used to lookup {@link SharedPreferences} the value is stored with.
     */
    public static int getCityID(Context context){
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getInt(PREF_CITY_ID, 0);
    }

    /**
     * Sets the cityName of the active user.
     *
     * @param context     Context used to lookup {@link SharedPreferences}
     * @param cityName The cityName of the user.
     */
    public static void setCityName(Context context, String cityName){
        SharedPreferences sharedPref = getSharedPreferences(context);
        sharedPref.edit().putString(PREF_CITY_NAME, cityName).apply();
    }

    /**
     * Returns the cityName of active user.
     *
     * @param context Context used to lookup {@link SharedPreferences} the value is stored with.
     */
    public static String getCityName(Context context){
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getString(PREF_CITY_NAME, "");
    }

    /**
     * Sets the storename of the active user.
     *
     * @param context     Context used to lookup {@link SharedPreferences}
     * @param delivery_charge The cityName of the user.
     */
    public static void setdelivery_charge(Context context, String delivery_charge){
        SharedPreferences sharedPref = getSharedPreferences(context);
        sharedPref.edit().putString(PREF_DELIVERYC, delivery_charge).apply();
    }

    /**
     * Returns the storename of active user.
     *
     * @param context Context used to lookup {@link SharedPreferences} the value is stored with.
     */
    public static String getdelivery_charge(Context context){
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getString(PREF_DELIVERYC, "");
    }


    /**
     * Sets the cityName of the active user.
     *
     * @param context     Context used to lookup {@link SharedPreferences}
     * @param discount_percentage The cityName of the user.
     */
    public static void setDiscount_percentage(Context context, String discount_percentage){
        SharedPreferences sharedPref = getSharedPreferences(context);
        sharedPref.edit().putString(PREF_DISCOUNT, discount_percentage).apply();
    }

    /**
     * Returns the cityName of active user.
     *
     * @param context Context used to lookup {@link SharedPreferences} the value is stored with.
     */
    public static String getDiscount_percentages(Context context){
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getString(PREF_DISCOUNT, "");
    }

    public static void setPrefdiscount_order_value(Context context, String discount_order_value){
        SharedPreferences sharedPref = getSharedPreferences(context);
        sharedPref.edit().putString(PREF_DISCOUNTA, discount_order_value).apply();
    }

    /**
     * Returns the storeemail of active user.
     *
     * @param context Context used to lookup {@link SharedPreferences} the value is stored with.
     */
    public static String getPrefdiscount_order_value(Context context){
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getString(PREF_DISCOUNTA, "");
    }

    public static void setPrefStorePhone(Context context, String storeemail){
        SharedPreferences sharedPref = getSharedPreferences(context);
        sharedPref.edit().putString(PREF_STORE_PHONE, storeemail).apply();
    }

    /**
     * Returns the storeemail of active user.
     *
     * @param context Context used to lookup {@link SharedPreferences} the value is stored with.
     */
    public static String getPrefMinimunDelivery(Context context){
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getString(PREF_MINIMUM_DELIVERY, "");
    }

    public static void setPrefMinimunDelivery(Context context, String storedel){
        SharedPreferences sharedPref = getSharedPreferences(context);
        sharedPref.edit().putString(PREF_MINIMUM_DELIVERY, storedel).apply();
    }


    public static String getstoreImg(Context context){
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getString(PREF_STORE_IMG, "");
    }

    public static void setstoreImg(Context context, String storeimg){
        SharedPreferences sharedPref = getSharedPreferences(context);
        sharedPref.edit().putString(PREF_STORE_IMG, storeimg).apply();
    }

    public static String getstoremname(Context context){
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getString(PREF_M_NAME, "");
    }

    public static void setstoremname(Context context, String mangername){
        SharedPreferences sharedPref = getSharedPreferences(context);
        sharedPref.edit().putString(PREF_M_NAME, mangername).apply();
    }
    /**
     * Returns the storeemail of active user.
     *
     * @param context Context used to lookup {@link SharedPreferences} the value is stored with.
     */
    public static String getPrefStorePhone(Context context){
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getString(PREF_STORE_PHONE, "");
    }
    /**
     * Sets the count of the active user.
     *
     * @param context     Context used to lookup {@link SharedPreferences}
     * @param count The count of the cart items
     */
    public static void setCartCount(Context context, int count){
        SharedPreferences sharedPref = getSharedPreferences(context);
        sharedPref.edit().putInt(PREF_CART_COUNT, count).apply();
    }

    /**
     * Returns the count of active user.
     *
     * @param context Context used to lookup {@link SharedPreferences} the value is stored with.
     */
    public static int getCartCount(Context context){
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getInt(PREF_CART_COUNT, 0);
    }

    /**
     * Sets the Banner image of the user City.
     *
     * @param context     Context used to lookup {@link SharedPreferences}
     * @param banners The banner images of the City
     */
    public static void setBannerImages(Context context, Set<String> banners){
        SharedPreferences sharedPref = getSharedPreferences(context);
        sharedPref.edit().putStringSet(PREF_BANNERS, banners).apply();
    }

    /**
     * Returns the Banners image of active user city.
     *
     * @param context Context used to lookup {@link SharedPreferences} the value is stored with.
     */
    public static Set<String> getBannerImages(Context context){
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getStringSet(PREF_BANNERS, null);
    }



    /**
     * Clear user details
     * */
    public static void clearActiveUserDetails(final Context context) {
        Log.d(TAG, "Clearing active account");
        SharedPreferences sp = getSharedPreferences(context);
        sp.edit()
                .remove(PREF_USER_ID)
                .remove(PREF_NAME)
                .remove(PREF_PHONE)
                .remove(PREF_EMAIL)
                .remove(PREF_PHOTO_URL)
                .remove(PREF_IS_TOKEN_UPLOAD)
                .remove(PREF_AREA_ID)
                .remove(PREF_AREA_NAME)
                .remove(PREF_CITY_ID)
                .remove(PREF_CITY_NAME)
                .remove(PREF_CART_COUNT)
                .remove(PREF_BANNERS)
                .apply();
    }

    /**
     * Reset all settings
     * */
    public static void resetSettings(final Context context) {
        Log.d(TAG, "Reset Settings");
        SharedPreferences sp = getDefaultSharedPreferences(context);
        sp.edit().clear().apply();
    }
}
