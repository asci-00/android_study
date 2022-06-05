# *Android Study*

---
## 📄 Activity

> 플랫폼 애플리케이션 모델의 기본 요소
> 
> 앱과 사용자의 상호작용을 위한 진입점 역할을 합니다. 활동은 Activity 클래스의 서브클래스로 구현
> 
> 활동은 앱이 UI를 그리는 창을 제공
> 
> 이 창은 일반적으로 화면을 채우지만 화면보다 작고 다른 창 위에 떠 있을 수 있음
> 
> 대부분의 앱은 여러 활동으로 구성 ( + Default Activity가 존재 )
> 
> 활동이 앱의 일관된 사용자 환경을 형성하기 위해 함께 작동하지만 
> 
> 각 활동은 다른 활동에 단지 느슨하게 결합 ( 일반적으로 앱의 활동 간에는 최소한의 종속성만 존재 )

```xml
<manifest ... >
  <application ... >
      <activity android:name=".ExampleActivity" />
      ...
  </application ... >
  ...
</manifest >
```
activity는 manifest 파일에 선언됨 ( 필수 property는 name ) 

### 활동 수명 주기 관리

> 활동은 수명 주기 전체 기간에 걸쳐 여러 상태를 거침
> 
> 상태 간 전환을 처리하는 데 일련의 콜백을 사용 가능

### `onCreate()`

- 시스템이 활동을 생성할 때 실행되는 이 콜백을 구현하며, <br/>구현 시 활동의 필수 구성요소를 초기화 하는 단계
    - 앱은 여기에서 뷰를 생성하고 데이터를 목록에 결합
    - 이 단계에서 setContentView()를 호출하여 활동의 사용자 인터페이스를 위한 레이아웃을 정의해야 함
    - onCreate()가 완료되면 다음 콜백은 항상 onStart()가 수행됨

### `onStart()`

- onCreate()가 종료되면 활동은 '시작됨' 상태로 전환되고 활동이 사용자에게 표시 
- 활동이 포그라운드로 나와서 대화형이 되기 위한 최종 준비에 준하는 작업을 포함

### `onResume()`

- 활동이 사용자와 상호작용을 시작하기 직전에 시스템은 이 콜백을 호출
- 이 시점에서 활동은 활동 스택의 가장 위에 있으며 모든 사용자 입력을 캡처
- 앱의 핵심 기능은 대부분 onResume() 메서드로 구현됨
- onPause() 콜백은 항상 onResume() 뒤에 위치

### `onPause()`

- 활동이 포커스를 잃고 '일시중지됨' 상태로 전환될 때 시스템은 onPause()를 호출
- 이 상태는 사용자가 *뒤로* 또는 *최근* 버튼을 탭할 때 발생
- 엄밀히 말하면 활동이 여전히 부분적으로 표시되지만<br/> 
  대체로 사용자가 활동을 떠나고 있으며 활동이 조만간 <br/>
  '중지됨' 또는 '다시 시작됨' 상태로 전환됨을 의미함
- 사용자가 UI 업데이트를 기다리고 있다면 '일시중지됨' 상태의 활동은 계속 UI를 업데이트할 수 있음 
    - 이러한 활동의 예에는 내비게이션 지도 화면 또는 미디어 플레이어 재생을 표시하는 활동이 포함됩니다. 
    - 이러한 활동이 포커스를 잃더라도 사용자는 UI가 계속 업데이트될 것으로 예상합니다.
- onPause() 완료 후 다음 콜백은 상황에 따라 onStop() 또는 onResume() 발생

*애플리케이션 또는 사용자 데이터를 저장하거나 네트워크를 호출하거나 데이터베이스 트랜잭션을 실행하는 데 onPause()를 사용해서는 안됨. [참조](https://developer.android.com/guide/components/activities/activity-lifecycle?hl=ko#saras)*

### `onStop()`

- 활동이 사용자에게 더 이상 표시되지 않을 때 시스템은 onStop()을 호출
- 활동이 제거 중이거나 새 활동이 시작 중이거나 기존 활동이 '다시 시작됨' 상태로 전환 중일때 발생됨
- 다음 콜백은 활동이 다시 시작되면 onRestart() / 활동이 완전히 종료되면 onDestroy()가 호출됨

### `onRestart()`

- '중지됨' 상태의 활동이 다시 시작되려고 할 때 시스템은 이 콜백을 호출
- onRestart()는 활동이 중지된 시간부터 활동 상태를 복원합니다.
- 이 콜백 뒤에 항상 onStart()이 호출됨

### `onDestroy()`
- 시스템은 활동이 제거되기 전에 이 콜백을 호출
- 이 콜백은 활동이 수신하는 마지막 콜백입니다. 
- 일반적으로 활동 또는 활동이 포함된 프로세스가 제거될 때 활동의 모든 리소스를 해제하도록 구현

![image](https://www.google.com/url?sa=i&url=https%3A%2F%2Fjamesdreaming.tistory.com%2F17&psig=AOvVaw3t0xr9zm3wiiF1tnoQ6Fbq&ust=1654508993452000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCIi92ImElvgCFQAAAAAdAAAAABAD)

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