# **Project SMITCH**

# **Components** 

# *UI*
MainActivity + MainViewModel -> To show the list of files
UpdateDeleteActivity + UpdateDeleteViewModel -> For updating and deleting files data

# *Data*
File -> Used as both DB entity and JSON object of files response
ApiResponse -> Wrapper for JSON Response

# *Database*
AppData base + FileDao -> For storing data in room database
FileRepo -> For getting and updating data to Room database

# *Network*
ApiInterface -> Has the definition for Retrofit APIs
ApiHelper -> Contains helper methods for accessing API requests.