# How to Add code snippet
- in the ThingWorx installation folder open the file
  ```..\Thingworx\Composer\node_modules\thingworx-ui-platform\dist\features\details\service-management\code-snippets\code-snippets.xml```
  - before the last line
  ```XML
  </code-snippets>
  ```
  add the following text:
  ```XML
  <code-snippet>
    <name>Run As</name>
    <description-key>tw.static-code-snippets.run-as.description</description-key>
    <tooltip-key>snippet-run-as</tooltip-key>
    <category>Security</category>
    <selections>
      <selection>
        <type>entity-picker</type>
        <tooltip-key>entity-picker</tooltip-key>
        <arguments>
          <entity-type>User</entity-type>
        </arguments>
        <result>user</result>
      </selection>
    </selections>
    <code><![CDATA[
      if (Resources["SecurityResource"].switchToUser({user: "<%=user%>"})) {
        try {
          // -- whatever code that needs to be run as "<%=user%>"
        } catch (exception) {
          // -- catch any exception
        } finally {
          Resources["SecurityResource"].comeBackToCurrentUser();
        }
      }
    ]]></code>
  </code-snippet>
  ```
- in the ThingWorx installation folder open the file
  ```..\Thingworx\Composer\node_modules\thingworx-ui-platform\dist\resources\locales\en\platform-details-translation.json```
  - find the node tw.code-snippets.tooltips and add the node
  ```JSON
  "snippet-run-as": "Run a piece of code as another user"
  ```
  - find the node tw.static-code-snippets and add the node
  ```JSON
  "run-as": {"description": "Run a piece of code as another user"}
  ```