# Play Flow Planner
The app provides users easily organizing the flow plans.

## Features
- Save/Edit/Delete Plan
- Save/Edit/Delete Actions
- Multi Languages
- Email/SMS/Reminder
- Room Persistence Library
- Custom List View
- Alert Dialog Fragment
- BroadcastReceiver
- Scheduled Notifications
## Files
### [Database](https://github.com/omansak/PlayFlowPlanner/tree/master/app/src/main/java/com/playcom/Database "Database")
 - [Dao](https://github.com/omansak/PlayFlowPlanner/tree/master/app/src/main/java/com/playcom/Database/Dao "DAO")
 - [Model](https://github.com/omansak/PlayFlowPlanner/tree/master/app/src/main/java/com/playcom/Database/Model "Model")
 - [Service](https://github.com/omansak/PlayFlowPlanner/tree/master/app/src/main/java/com/playcom/Database/Service "Service")
 - [Appdatabase.java](https://github.com/omansak/PlayFlowPlanner/blob/master/app/src/main/java/com/playcom/Database/AppDatabase.java "Appdatabase.java")

[![Database Files](https://github.com/omansak/PlayFlowPlanner/blob/master/Images/db-files.PNG "Database Files")](https://github.com/omansak/PlayFlowPlanner/blob/master/Images/db-files.PNG "Database Files")

### [UI Backend](https://github.com/omansak/PlayFlowPlanner/tree/master/app/src/main/java/com/playcom/playflowplanner "UI Backend")
- [Custom List Adapters (BaseAdapter)](https://github.com/omansak/PlayFlowPlanner/tree/master/app/src/main/java/com/playcom/playflowplanner/ListAdapters "Custom List Adapters (BaseAdapter)")
- [ Dialogs (DialogFragment / Alert Dialog)](https://github.com/omansak/PlayFlowPlanner/tree/master/app/src/main/java/com/playcom/playflowplanner/Dialog " Dialogs (DialogFragment / Alert Dialog)")
- [Activities](https://github.com/omansak/PlayFlowPlanner/tree/master/app/src/main/java/com/playcom/playflowplanner "Activities")
- [Scheduled Notifications (BroadcastReceiver)](https://github.com/omansak/PlayFlowPlanner/blob/master/app/src/main/java/com/playcom/playflowplanner/Functions/NotificationFunction.java "Scheduled Notifications (BroadcastReceiver)")

[![UI Backend](https://github.com/omansak/PlayFlowPlanner/blob/master/Images/ui-backend.PNG "UI Backend")](https://github.com/omansak/PlayFlowPlanner/blob/master/Images/ui-backend.PNG "UI Backend")
### [UI Frontend](https://github.com/omansak/PlayFlowPlanner/tree/master/app/src/main/res "UI Frontend")

[![UI Frontend](https://github.com/omansak/PlayFlowPlanner/blob/master/Images/ui-frontend.PNG "UI Frontend")](https://github.com/omansak/PlayFlowPlanner/blob/master/Images/ui-frontend.PNG "UI Frontend")

### Permissions
- android.permission.WAKE_LOCK
### Activities
- HomeActivity
- ActionActivity
- FunctionListActivity
- SettingsActivity
## Receivers
- Functions.NotificationFunction
## Build Setup
### Prerequisites for both Android Studio and Gradle

* Download and install the Android SDK. Make sure to install the Google APIs for your API level (e.g., 28), the Android SDK Build-tools version for your buildToolsVersion version, and the Android Support Repository and Google Repository.
 - Compile SDK Version 28
 - Min SDK Version 21
 - Target SDK Version 28
* Sync android.arch.persistence.room from grandle.build.

### Building in Android Studio
1. Download and install the latest version of [Android Studio](http://developer.android.com/sdk/installing/studio.html).
2. In Android Studio, choose "Import Project" at the welcome screen.
3. Browse to the location of the project, and double-click on the project directory.
4. If prompted with options, check "Use auto-import", and select "Use default gradle wrapper (recommended)".  Click "Ok".
5. Click the green play button (or 'Shift->F10') to run the project!

### Dependencies
- com.android.support appcompat-v7:28.0.0
- com.android.support.constraint constraint-layout:1.1.3
- com.android.support design:28.0.0
- com.google.android.gms play-services-maps:16.0.0
- junit junit 4.12
- com.android.support.test runner:1.0.2
- com.android.support.test.espresso espresso-core:3.0.2
- android.arch.persistence.room runtime:1.1.1

## Database Desing
[![](https://github.com/omansak/PlayFlowPlanner/blob/master/Images/db-desing.PNG)](http://https://github.com/omansak/PlayFlowPlanner/blob/master/Images/db-desing.PNG)
## In-App Images
[![home](https://github.com/omansak/PlayFlowPlanner/blob/master/Images/home.PNG "home")](http://https://github.com/omansak/PlayFlowPlanner/blob/master/Images/home.PNG "home")[![actions](https://github.com/omansak/PlayFlowPlanner/blob/master/Images/actions.PNG "actions")](https://github.com/omansak/PlayFlowPlanner/blob/master/Images/actions.PNG "actions")[![functions](https://github.com/omansak/PlayFlowPlanner/blob/master/Images/functions.PNG "functions")](https://github.com/omansak/PlayFlowPlanner/blob/master/Images/functions.PNG "functions")[![](https://github.com/omansak/PlayFlowPlanner/blob/master/Images/mail.PNG)](https://github.com/omansak/PlayFlowPlanner/blob/master/Images/mail.PNG)[![](https://github.com/omansak/PlayFlowPlanner/blob/master/Images/sms.PNG)](https://github.com/omansak/PlayFlowPlanner/blob/master/Images/sms.PNG)[![](https://github.com/omansak/PlayFlowPlanner/blob/master/Images/add-actions.PNG)](https://github.com/omansak/PlayFlowPlanner/blob/master/Images/add-actions.PNG)[![](https://github.com/omansak/PlayFlowPlanner/blob/master/Images/add-plan.PNG)](https://github.com/omansak/PlayFlowPlanner/blob/master/Images/add-plan.PNG)[![](https://github.com/omansak/PlayFlowPlanner/blob/master/Images/notifications.PNG)](https://github.com/omansak/PlayFlowPlanner/blob/master/Images/notifications.PNG)[![](https://github.com/omansak/PlayFlowPlanner/blob/master/Images/Settings.PNG)](https://github.com/omansak/PlayFlowPlanner/blob/master/Images/Settings.PNG)
## Collaborators
* [DEMET 'DA' AKYOL](https://github.com/DemetAkyol "DEMET 'DA' AKYOL")
* [OSMAN ŞAKİR 'OMANSAK' KAPAR](https://github.com/omansak "OSMAN ŞAKİR 'OMANSAK' KAPAR")
