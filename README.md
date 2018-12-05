# AndroidBaseProject
A base project to fast track mobile development.This project is setup to implement the Model-View-Presenter
architecture for mobile development, it is also suggested to follow the packaging by feature style of structure.





## Libraries Setup Include:
   - [Android Support Library:25.3.1](https://developer.android.com/topic/libraries/support-library/revisions.html)
   - [Retrofit Networking](http://square.github.io/retrofit/)
   - [Butterknife:8.8.1](http://jakewharton.github.io/butterknife/)





## Contribution guide
 [Guide to contributing to CottaCush Android Projects](https://github.com/CottaCush/android-guidelines/blob/master/project_style_guidelines.md)





## CheckStyle:
 <h5>Exisiting projects</h5>

   ```To Setup CheckStyle for Already existing projects kindly follow the instructions below: ```
   - cd to the root directory of your project
   - do ```mkdir config && cd "$_"```
   ( You can also do  ```mkdir config ``` then subsequently ```cd config``` or whichever way is convenient for you to create the config directory and cd into it)
 - do  ``` curl -L -O https://link/to/raw/baseproject/checkstyle.xml ```
  and  ``` curl -L -O https://link/to/raw/baseproject/prepush-checks.sh ``` to download the checkstyle and prepush script respectively to your config directory.
-  Move on to next sets of instructions.

     <h5>For fresh projects</h5>

   ```To Setup CheckStyle for fresh projects that are offsprings of this baseproject's post-lint era (i.e have the scripts in the appropriate directory) ```

-   In your root directory do ```chmod u+x config/prepush-checks.sh```
- Then do ```ln -s  $PWD/config/prepush-checks.sh    .git/hooks/pre-push```

Subsequently git pushes to the master repo will either fail or pass. For failed pushes find the issues at ```<path-to-repo/app/build/reports/checkstyle/checkstyle.xml``` and handle accordingly


><h6>Note :The appropriate Url for the checkstyle.xml and prepush check scripts will be updated after both files are hosted on this repository and this Note will be removed</h6


