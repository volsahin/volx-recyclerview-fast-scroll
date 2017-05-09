[![](https://jitpack.io/v/volsahin/volx-recyclerview-fast-scroll.svg)](https://jitpack.io/#volsahin/volx-recyclerview-fast-scroll)

# volx-recyclerview-fast-scroll
An easy to use implementation for fast scroll recyclerview


![alt tag](http://i.imgur.com/yQat7Nj.gif)
## Usage

### Minimal working example

In your adapter file implement IVolxAdapter and return your data as a list of objects:

```java

public class UsersOwnAdapter extends RecyclerView.Adapter<UsersOwnAdapter.ViewHolder> implements IVolxAdapter {

    private List<UserModel> mDataset;

    @Override
    public List<Object> getList() {
        return new ArrayList<Object>(mDataset);
    }

```
In your model file add @ValueArea annotation for the field that be rendered as a fast-scroll list

```java

public class UserModel {

    private int example;
    
    @ValueArea
    private String name;
}

```

In your activity/fragment or wherever you have reference of your  parent frame layout and your recyclerview:

```java

         new Volx.Builder()
                .setUserRecyclerView(mRecyclerView)
                .setParentLayout(parentLayout)
                .build();

```

Don't forget to sort your list before building Volx

    
### More On Customization

If you want to customize colors, text sizes and layout sizes you can call it like:

```java

	new Volx.Builder()
                .setUserRecyclerView(mRecyclerView)
                .setParentLayout(parentLayout)
                .setActiveColor(Color.CYAN) // the lightened color of right bar
                .setBackgroundColor(Color.BLACK) // the color of right bar
                .setTextColor(Color.WHITE) // the color of right bar letters
                .setBarWidth(24) // the width of right bar in dp
                .setBarHeightRatio(1f) // the height of right bar between 0 and 1 according to screen height
                .setTextSize(0) // the size of the letters in right bar , default zero handles it nicely , specific values are optional
                .setMiddleTextSize(16) // the size of the letter in center circle
                .setMiddleLayoutSize(48) // the size of the center circle in dp
                .setMiddleBackgroundColor(Color.rgb(67, 67, 67)) // the color of the center circle
                .setMiddleTextColor(Color.WHITE)  // the color of the letter in center circle
                .setMiddleStrokeWidth(4) // the width of center circle stroke in dp
                .setMiddleStrokeColor(Color.BLACK) // the color of right bar stroke
                .setRightStrokeWidth(3) // the width of right bar stroke in dp
                .setRightStrokeColor(Color.rgb(204, 204, 204)) // the color of middle circle stroke
                .setMinItem(0) // the min amount of item required to show right bar
                .setDelayMillis(3000) // the amount of time in ms that closes right bar if no interaction , if negative it never closes
                .build();

```
If you want to make volx invisible and remove it's listeners you can call function with your object:

```java
	volx.setInactive(true);
```
Later you can activate volx again:

```java
	if (volx.isInactive())
            volx.setInactive(false);
```
## Download

### Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```groovy

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

### Step 2. Add the dependency

```groovy

	dependencies {
		compile 'com.github.volsahin:volx-recyclerview-fast-scroll:v1.0.4'
	}
  
```

## License

    Copyright 2017 Volkan Şahin

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.



