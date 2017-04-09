[![](https://jitpack.io/v/volsahin/volx-recyclerview-fast-scroll.svg)](https://jitpack.io/#volsahin/volx-recyclerview-fast-scroll)

# volx-recyclerview-fast-scroll
An easy to use implementation for fast scroll recyclerview

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

In your activity/fragment or whereever you have reference of your  parent frame layout and your recyclerview:

```java

         new Volx.Builder()
                .setUserRecyclerView(mRecyclerView)
                .setParentLayout(parentLayout)
                .build();

```
    
### More On Customization

If you want to customize colors, text sizes and layout sizes you can call it like:

```java

        new Volx.Builder()
                .setUserRecyclerView(mRecyclerView) // the recycler view that needs fast scrolling
                .setParentLayout(parentLayout) // the parent frame layout
                .setActiveColor(Color.RED) // the lightened color of right bar
                .setBackgroundColor(Color.YELLOW) // the color of right bar
                .setTextColor(Color.WHITE) // the color of right bar letters
                .setBarWidth(96) // the width of right bar in dp
                .setTextSize(26) // the size of the letters in right bar
                .setMiddleTextSize(32) // the size of the letter in center circle
                .setMiddleLayoutSize(96) // the size of the center circle in dp
                .setMiddleBackgroundColor(Color.GREEN) // the color of the center circle
                .setMiddleTextColor(Color.CYAN)  // the color of the letter in center circle
                .setDelayMillis(3000) // the amount of time in ms that closes right bar if there are no interaction
                .build();

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
		compile 'com.github.volsahin:volx-recyclerview-fast-scroll:-SNAPSHOT'
	}
  
```




