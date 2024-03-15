# SecurityResource
An extension to manage security.

**This Extension is provided as-is and without warranty or support. It is not part of the PTC product suite and there is no PTC support.**

## Description
This extension adds a Resource object providing utility services for security management.

## Services
- *switchToUser*: switches the security context to another user (in this way it is possible to run a service as another user)
  - input
    - user: the user - USERNAME (No default value)
  - output: true if the user switching has been performed, false otherwise - BOOLEAN
- *comeBackToCurrentUser*: comes back to the current user (to use exclusively after a *switchToUser* call)
  - input: NOTHING
  - output: NOTHING

## Usage Samples
The best way to use the services is as follows:

```javascript
if (Resources["SecurityResource"].switchToUser({user: "<USER>"})) {
    try {
        // -- whatever code that needs to be run as <USER>
    } catch (exception) {
        // -- catch any exception
    } finally {
        Resources["SecurityResource"].comeBackToCurrentUser();
    }
}
```

Note that:
- \<USER\> has to be visible by the current user
- SecurityResource has to be visible by the current user
- current user needs to have execution permission on service *switchToUser*
- \<USER\> needs to have execution permission on service *comeBackToCurrentUser*

## Donate
If you would like to support the development of this and/or other extensions, consider making a [donation](https://www.paypal.com/donate/?business=HCDX9BAEYDF4C&no_recurring=0&currency_code=EUR).
