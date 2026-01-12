
// function check_login(){
//     var op_on = document.getElementById("txton");
//         op_on.onclick = function() {
//         var op_name = document.getElementById("txtID").value;
//         var op_password = document.getElementById("txtPWD").value;
        
//         if(op_name.length<3||op_name.length>8){
//             alert("你输入的用户名少于两位或者大于八位");
//         }
//         else if(op_password.length<6||op_password.length>16){
//             alert("你输入的密码少于六位或者大于十六位");
//         }
//         else{
            
//                 alert("开始登录验证...")
//                 var inputname=op_name;
//                 var inputpassword=op_password;
//                 alert("开始连接数据库...")
//                 alert("数据库连接完毕...")
//                 MongoClient.connect(url,{ useNewUrlParser: true, useUnifiedTopology: true }, function(err, db) {
//                     if (err) throw err;
//                     var dbo = db.db("Blog");
//                     var whereStr = {"name":inputname,"password":inputpassword};  // 查询条件
//                     dbo.collection("user").find(whereStr).toArray(function(err, result) {
//                         if (err){
//                             throw err;
//                         } 

//                         if(result.length > 0){
//                             console.log('登录成功');
//                         }
//                         else{
//                             console.log('登录失败');
//                         }
//                         db.close();
//                     });
//                 });
// 			}
//     }
// }


// var inputname='ZSJ';
// var inputpassword='123456'
// var MongoClient = require('mongodb').MongoClient;
// var url = "mongodb://localhost:27017/";
// MongoClient.connect(url,{ useNewUrlParser: true, useUnifiedTopology: true }, function(err, db) {
//     if (err) throw err;
//     var dbo = db.db("Blog");
//     var whereStr = {"name":inputname,"password":inputpassword};  // 查询条件
//     dbo.collection("user").find(whereStr).toArray(function(err, result) {
//         if (err){
//             throw err;
//         } 

//         if(result.length > 0){
//             console.log('登录成功');
//         }
//         else{
//             console.log('登录失败');
//         }
//         db.close();
//     });
// });