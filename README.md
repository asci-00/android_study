# *Android Study*

### 크기 단위

> 모바일 디바이스는 다양한 크기와 해상도를 가지고 있음
> 
> 이에 따라 다양한 디바이스에 호환이 되도록 어플리케이션을 개발해야함 
> 
> 이러한 방법으로 크기 단위에 대한 이해가 필요하며, 
> 
> `대체 레이아웃` 그리고 `ConstraintLayout` 등의 기능을 사용하여야 한다. 

### `px`

> 실제 pixel으로, 해상도가 다를 경우, 크기가 다르며 이미지가 깨질 수 있음

### `in` *Inch*

> 실제 물리적인 길이 2.54cm

### `mm` *milli meter*

> 실제 물리적인 milli meter

### `dp/dpi`

> 화면의 크기를 기준으로, 화면의 해상도랑 별개로 같은 비율로 출력함
> 
> px의 한계점을 극복하기 위해 자주 사용됨
> 
> dp = px * (160/디바이스 dpi)

### `dpi` *dots per inch*

> 1인치에 포함되는 픽셀 수 (default: 160) 
> 
> 높을 수록 선명한 이미지 및 텍스트 표시

### `em`

> 글꼴과 별개로 동일한 텍스트 크기 출력

### `sp/sip` 

> dp와 비슷한 개념이지만, 글꼴 단위로 자주 쓰이며 사용자가 선택한 글꼴에 따라 효과가 다름




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