[![npm][npm-badge]][npm]
[![react-native][rn-badge]][rn]
[![MIT][license-badge]][license]
[![bitHound Score][bithound-badge]][bithound]
[![Downloads](https://img.shields.io/npm/dm/rnkit-meiqia.svg)](https://www.npmjs.com/package/rnkit-meiqia)

美洽 for [React Native][rn].

[**Support me with a Follow**](https://github.com/simman/followers)

[npm-badge]: https://img.shields.io/npm/v/rnkit-meiqia.svg
[npm]: https://www.npmjs.com/package/rnkit-meiqia
[rn-badge]: https://img.shields.io/badge/react--native-v0.46-05A5D1.svg
[rn]: https://facebook.github.io/react-native
[license-badge]: https://img.shields.io/dub/l/vibe-d.svg
[license]: https://raw.githubusercontent.com/rnkit/rnkit-meiqia/master/LICENSE
[bithound-badge]: https://www.bithound.io/github/rnkit/rnkit-meiqia/badges/score.svg
[bithound]: https://www.bithound.io/github/rnkit/rnkit-meiqia

## Getting Started

First, `cd` to your RN project directory, and install RNMK through [rnpm](https://github.com/rnpm/rnpm) . If you don't have rnpm, you can install RNMK from npm with the command `npm i -S rnkit-meiqia` and link it manually (see below).

### iOS

  `$npm install -S rnkit-meiqia`

  `$react-native link rnkit-meiqia`


#### Manually
1. Add `node_modules/rnkit-meiqia/ios/RNKitMeiQia.xcodeproj` to your xcode project, usually under the `Libraries` group
1. Add `libRNKitMeiQia.a` (from `Products` under `RNKitMeiQia.xcodeproj`) to build target's `Linked Frameworks and Libraries` list



### Android

  `$npm install -S rnkit-meiqia`

  `$react-native link rnkit-meiqia`

#### Manually
1. JDK 7+ is required
1. Add the following snippet to your `android/settings.gradle`:

  ```gradle
include ':rnkit-meiqia'
project(':rnkit-meiqia').projectDir = new File(rootProject.projectDir, '../node_modules/rnkit-meiqia/android/app')
  ```
  
1. Declare the dependency in your `android/app/build.gradle`
  
  ```gradle
  dependencies {
      ...
      compile project(':rnkit-meiqia')
  }
  ```
  
1. Import `import iio.rnkit.meiqia.MeiQiaPackage;` and register it in your `MainActivity` (or equivalent, RN >= 0.32 MainApplication.java):

  ```java
  @Override
  protected List<ReactPackage> getPackages() {
      return Arrays.asList(
              new MainReactPackage(),
              new MeiQiaPackage()
      );
  }
  ```

Finally, you're good to go, feel free to require `rnkit-meiqia` in your JS files.

Have fun! :metal:

## Basic Usage

Import library

```
import MeiQia from 'rnkit-meiqia';
```

### Code

#### Init

```jsx
  try {
    const clientId = await MeiQia.init('meiqia_app_key')
  } catch (err) {

  }
```

#### SetClientInfo

```jsx
  try {
    await MeiQia.setClientInfo({});
  } catch (err) {

  }
```

#### Open Chat

```jsx
  MeiQia.open()
```

#### openService

```jsx
  MeiQia.openService()
```

#### closeService

```jsx
  MeiQia.closeService()
```


## Contribution

- [@simamn](mailto:liwei0990@gmail.com) The main author.

## Questions

Feel free to [contact me](mailto:liwei0990@gmail.com) or [create an issue](https://github.com/rnkit/rnkit-meiqia/issues/new)

> made with ♥


