# *Android Study*

### `Manifest`

```xml
<!-- AnroidManifest.xml -->
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.first_project">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher" <!-- app의 기본 이미지 -->
        android:label="@string/app_name" <!-- app의 기본 이름 -->
        android:roundIcon="@mipmap/ic_launcher_round" <!-- 테마에 따른 rounded app 이미지 -->
        android:supportsRtl="true" 
        android:theme="@style/Theme.First_project"> <!-- 테마에 따른 app의 기본 테마 -->
    
        <!-- app에서 사용하는 모든 activity가 등록됨 activity를 새로 만들 경우 새로 생김-->
        <activity 
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter> <!-- main activity에만 존재하는 markup으로 처음 보여지는 activity를 설정 -->
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity android:name=".SubActivity"/>
    </application>

</manifest>
```

### `Resource`

```markdown
res
├─drawable              // drawable: image
├─layout                // layout: activity가 보여지는 layout xml 파일들
├─mipmap-anydpi-v26     // minmap: 해상도 별 안드로이드 아이콘들
├─mipmap-hdpi
├─mipmap-mdpi
├─mipmap-xhdpi
├─mipmap-xxhdpi
├─mipmap-xxxhdpi
├─values                // 기본적으로 colors, strings, styles 등 정적 값 리소스
└─values-night

```