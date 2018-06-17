The basic selection demo for the items shown inside the recyclerview.

We can long press any item, then the  selection mode will be initiated.
In the selection mode we can select any/all messages, unselect, etc.
The selected messages can then be used to perform the actions, in this demo app we are supporting 2 actions:
 a. Delete the selected messages
 b. Mark the unread messages as read
 
Also it uses the MVP pattern here, so the functionality is all inside the Presenter, 
and the UI components are minimilly aware of any bussuness logics.

It uses the generic classes which can be extended easily.
Basic functionality lies in BaseSelectionPresenter which handles all 
the different things and then passes on the callback to perform various things to the caller component.

Example to extend this is as shown in this app, 
as done with the  MessageListFragment we can use any fragment by extending the BaseSelectionFragment to handle it.
