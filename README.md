# PinCodePicker

### Version: 0.0.3

[![License](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-PinCodePicker-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/1725)

### Description

PinCodePicker was created for Android platform as a view which allows to take passwords/codes or some other sensitive data from end user in easy way, so developers can focus on the core functionalities of their application!

Notice that PinCodePicker is a project under huge development ;) (at least should be)

#### Welcome to fork and pull request.

### Screenshot
![image](art/pin_code_picker_0_0_3.gif)

### Quick Setup（Basic Usage)

#### XML attributes support:
```xml
<attr name="pin_code_length" format="integer"/>
<attr name="pin_code_type" format="string"/>
<attr name="pin_code_animation_current" format="reference"/>
<attr name="pin_code_filled_out_drawable" format="reference"/>
```

##### 1.Integration

###### Using Gradle:

```groovy
dependencies {
    compile 'com.github.polok.pincodepicker:pincodepicker:0.0.3'
}
```

###### Using Maven:

```xml
<dependency>
    <groupId>com.github.polok.pincodepicker</groupId>
    <artifactId>pincodepicker</artifactId>
    <version>0.0.3</version>
</dependency>
```

##### 2.Usage

###### Add picker into our UI (set pincode length)
```xml
    <com.github.polok.pincodepicker.PinCodeRecyclerView
        android:id="@+id/list"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"
        android:background="#3E464C"
        pin_code:pin_code_length="6"/>
```

###### Set listener to be notified when whole pin code was entered

```java
PinCodeRecyclerView view = (PinCodeRecyclerView) findViewById(R.id.list);
    view.setPincodeListener(new PinCodeListener() {
        @Override
        public void onPinCodeInserted(String pinCode) {
            Toast.makeText(MainActivity.this, "Whole code", Toast.LENGTH_SHORT).show();
        }
    });
```

###### You can also validate whole entered pin code and check whether it's equal to a specified value

```java
view.setPinCodeValidation(new PinCodeValidation() {
    @Override
    public CharSequence getCorrectPinCode() {
        return "012345";
    }

    @Override
    public void onPinCodeCorrect(String pinCode) {
        Toast.makeText(MainActivity.this, "Code is correct", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPinCodeError(String pinCode) {
        Toast.makeText(MainActivity.this, "Code is NOT valid", Toast.LENGTH_SHORT).show();
    }
});
```

#### If you want to see more details, go ahead and check the demo!

License
--------

    Copyright 2015 Marcin Polak

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

