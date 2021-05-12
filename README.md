## STL - Android Code Challenge

This Android App was proudly developed by <b>Rapheal Djane Kotei</b> for SuperTech LTD 

<hr>

## The APP is made up of two Activities:

1. The First Activity retrives a list of books from the Open Library Books API and nicely displays it in a listview.

2. The Second Activity displays the details of the book, on click of an item in the First Activity.

## Features and Functionality

1. The App allows you to bookmark your favourite book(s)

2. The App has a progress bar that notifies the user when the API is been loaded.

## Android Manifest

<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.android_code_challenge.stl">

    <uses-permission android:name="android.permission.INTERNET" />

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.STL">
        <activity android:name=".OpenLibraryBooks">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity android:name=".OpenLibraryBookDetails"/>
    </application>

</manifest>


