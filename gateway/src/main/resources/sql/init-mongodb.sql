mongo --port 27017 --host localhost -u root -p radar --authenticationDatabase "admin"
#mongo --port 27017 --host localhost -u hunter -p abcdef --authenticationDatabase "securitydb"
use securitydb;
#db.auth("hunter","abcdef");

#清楚数据库
db.user.drop();
db.role.drop();
db.role_user.drop();
db.permission.drop();
db.permission_role.drop();

db.grantRolesToUser ( "root", [ { role: "__system", db: "admin" } ] )
db.dropDatabase();
db.dropUser("hunter");
show users;
exit;

#重新创建数据库
mongo --port 27017 --host localhost -u root -p radar --authenticationDatabase "admin"

use securitydb;
db.createUser({user: "hunter",pwd:"abcdef",roles: [{role:"readWrite",db:"securitydb"}]});
#db.updateUser("hunter",{roles: [{role:"readWrite",db:"securitydb"} ]});
#db.updateUser("hunter",{pwd:"abcdef"});
db.auth("hunter","abcdef");
exit;

mongo --port 27017 --host localhost -u hunter -p abcdef --authenticationDatabase "securitydb"
use securitydb;
db.createCollection("user");
db.user.insert({userid:1,username:"admin",password:"admin"});
db.user.insert({userid:2,username:"hunter",password:"abc"});
db.user.insert({userid:3,username:"admin",password:"admin"});
db.user.insert({userid:4,username:"edison",password:"123"});

db.createCollection("role");
db.role.insert({roleid:1,rolename:"ROLE_ADMIN"});
db.role.insert({roleid:2,rolename:"ROLE_USER"});

db.createCollection("role_user");
db.role_user.insert({roleuserid:1,userid:1, roleid:1});
db.role_user.insert({roleuserid:2,userid:1, roleid:2});

db.createCollection("permission");
db.permission.insert({permissionid:1, name:'ROLE_HOME', decription:'home', url:'/', pid:null});
db.permission.insert({permissionid:2, name:'ROLE_ADMIN', decription:'hunter', url:'/admin', pid:null});

db.createCollection("permission_role");
db.permission_role.insert({proleid:1, roleid:1, permissionid:1});
db.permission_role.insert({proleid:2, roleid:1, permissionid:2});
db.permission_role.insert({proleid:3, roleid:2, permissionid:1});

db.user.find();
db.role.find();

exit;
