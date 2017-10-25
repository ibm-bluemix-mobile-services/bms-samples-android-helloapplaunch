IBM Bluemix App Launch Sample Android HelloAppLaunch
=====================================================

The [Bluemix App Launch service](https://console.stage1.bluemix.net/catalog/services/app-launch) App Launch service on Bluemix helps in controlled reach of app features. It provides a unified service to customize and personalize your applications to different audience with just few clicks.

Ensure that you go through [Bluemix App Launch service documentation](https://console-regional.ng.bluemix.net/docs/services/app-launch/index.html) before you start.

## Contents
- [Setup App Launch Service](#setup-app-launch-service)
	 - [Creating the service](#creating-the-service)
	 - [Creating a feature](#creating-a-feature)
	 - [Creating an audience](#creating-an-audience)
	 - [Creating an engagement](#creating-an-engagement)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Initialize SDK](#initialize-sdk)
    - [Include client App Launch SDK](#include-client-app-launch-sdk)
    - [Initialize](#initialize) 
    - [Register](#register) 
    - [Update User](#update-user)   
- [Actions](#actions)
- [Feature Toggle](#feature-toggle)
    - [Check if feature is enabled](#check-if-feature-is-enabled)
    - [Get variable for feature](#get-variable-for-feature)
- [Metrics](#metrics)
    - [Send Metrics](#send-metrics)
- [Applaunch Android SDK](#applaunch-android-sdk)    
- [Samples and videos](#samples-and-videos)

##Setup App Launch Service
### Creating the service
![Create feature](https://github.com/ibm-bluemix-mobile-services/bms-clientsdk-android-applaunch/blob/development/Images/create_service.gif)
### Creating a feature
![Create feature](https://github.com/ibm-bluemix-mobile-services/bms-clientsdk-android-applaunch/blob/development/Images/create_feature.gif)
### Creating an audience
![Create audience](https://github.com/ibm-bluemix-mobile-services/bms-clientsdk-android-applaunch/blob/development/Images/create_audience.gif)
### Creating an engagement
![Create engagement](https://github.com/ibm-bluemix-mobile-services/bms-clientsdk-android-applaunch/blob/development/Images/create_engagement.gif)

## Prerequisites

 * Android API level 14 or later
 * Android 4.0 or later
 * [Android Studio](https://developer.android.com/studio/index.html)
 * [Gradle](https://gradle.org/install)
 * [BMSCore](https://github.com/ibm-bluemix-mobile-services/bms-clientsdk-android-core) SDKs installed by using 
  either Android Studio or Gradle

## Installation

Choose to integrate the App Launch Service Android Client SDK package using either of the following options:

- Download and import the package to your Android Studio project

## Initialize SDK


### Include client App Launch SDK 

Import [applaunch-client-android.aar](https://github.com/sam-almighty/bms-clientsdk-android-applaunch/raw/development/dist/applaunch-client-android.aar) as a module into the project and Configure the app module `build.gradle` files.

1. Add Bluemix App Launch Android SDK dependency and BMS Core dependency to your app module `build.gradle` file.
    
    ```
    dependencies {
        ........
        compile project(':applaunch-client-android')
         compile 'com.ibm.mobilefirstplatform.clientsdk.android:core:[2.0.0,3.0.0)'
        .......
    }
    ```
2. Configure the `AndroidManifest.xml` file. Refer the [example here](https://github.ibm.com/Engage/bms-samples-android-helloengage/blob/master/PizzaDelivery/app/src/main/AndroidManifest.xml). Add the following permissions inside application's `AndroidManifest.xml` file. 

     ```
     <uses-permission android:name="android.permission.INTERNET"/>
     <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
     <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
     ```
### Initialize
A common place to put the initialization code is the`onCreate()`method of the `main activity` in your Android application: 

```
// Initialize the SDK
  AppLaunch.getInstance().initApp(getApplication(), "bluemixRegionSuffix","appGUID","clientSecret");
```

Where `bluemixRegionSuffix` specifies the location where the app is hosted. You can use any of the following values:

- `BMSClient.REGION_US_SOUTH`
- `BMSClient.REGION_UK`
- `BMSClient.REGION_SYDNEY`

The `appGUID` is the app launch app GUID value, while `clientSecret` is the appLaunch client secret value which can be obtained from the service console.

**Note: initApp should be the first call in the application.**
Edit the sample applications ```MainActivity.java``` to update your service details

### Register
To register the user invoke ```AppLaunch.getInstance().registerUser()``` api: 

```
// Register the user
        AppLaunchParameters appLaunchParameters = new AppLaunchParameters();
        appLaunchParameters.put("customerType","platinum");
        AppLaunch.getInstance().registerUser("userId", appLaunchParameters,new AppLaunchResponseListener() {
            @Override
            public void onSuccess(AppLaunchResponse appLaunchResponse) {
               
           }

            @Override
            public void onFailure(AppLaunchFailResponse appLaunchFailResponse) {
          
            }
        });
```

## Actions

### Get Actons

Use the ` AppLaunch.getInstance().getActions()` API to fetch all the actions assosicated with the application. 

     AppLaunch.getInstance().getActions(AppLaunchActions);

Here AppLaunchActions is an interface which has to be implemented in the application. The interface provides callback methods which gets triggered if the features are present in the actions.        


## Feature Toggle

### Check if feature is enabled

Use the ` AppLaunch.getInstance().isFeatureEnabled()` API to check if a particular feature is enabled for the application. This api returns true if the feature is enable for the application else false.


     AppLaunch.getInstance().isFeatureEnabled(featureCode)
 
 **Note:Throws AppLaunchException if isFeatureEnabled is invoked before getActions() api.**    
Edit the sample applications ```PizzaDetailsActivity.java``` to update your feature code

### Get variable for feature
Use the `AppLaunch.getInstance().getVariableForFeature()` to fetch the variable corresponding to a feature

    AppLaunch.getInstance().getVariableForFeature("featurecode","variablecode");
    

This api returns the varaible corresponding to the variable code for a particular feature.

 **Note:Throws AppLaunchException if getVariableForFeature is invoked before getActions() api.** 
 
Edit the sample applications ```PizzaDetailsActivity.java``` to update your featurecode and variablecode

## Metrics

### Send Metrics

To send metrics to the server use the `AppLaunch.getInstance().sendMetrics();` api. This sends the metrics information to the server

```
 AppLaunch.getInstance().sendMetrics("metriccode");
```
Edit the sample applications ```PizzaDetailsActivity.java``` to update your metriccode

## Applaunch Android SDK

For more information on the Applaunch Android SDK api, visit - [AppLaunch Android SDK](https://github.com/ibm-bluemix-mobile-services/bms-clientsdk-android-applaunch/blob/development/README.md#prerequisites)

## Samples and videos

* For samples, visit - [Github Sample](https://github.com/ibm-bluemix-mobile-services/bms-samples-android-helloapplaunch)


### Learning More

* Visit the **[Bluemix Developers Community](https://developer.ibm.com/bluemix/)**.

### Connect with Bluemix

[Twitter](https://twitter.com/ibmbluemix)|
[YouTube](https://www.youtube.com/watch?v=dQ1WcY_Ill4) |
[Blog](https://developer.ibm.com/bluemix/blog/) |
[Facebook](https://www.facebook.com/ibmbluemix) |
[Meetup](http://www.meetup.com/bluemix/)

=======================
Copyright 2016-17 IBM Corp.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
