#大三上学期课程设计项目《中软国际文库》-俞育峰  
#主要功能
在线浏览office文档，并提供下载  
个人文档的管理，包括增删改查  
对文档类别的增删改查  
通过文档类别查看文档  
通过全文检索搜索文档  
对文档评论  
#使用技术  
Struts2 Hibernate4 Spring4  
Spring Data JPA （管理持久层）  
Lucene 5 （全文检索）  
UEditor （富文本编辑器）  
WebUploader （文件上传)  
OpenOffice swfTools Office （文件转换 swf显示）  
#使用
1.安装 OpenOffice 并在com.yyf.actions.UploadAction 的 getOfficeConvert()中写入OpenOffice程序位置    
2.修改 getOfficeConvert() 中 imp.setPdf2swf("D:\\DevInstall\\SWFTools\\pdf2swf.exe"); swf转换程序位置  
3.文件的上传目录 com.yyf.utils.FileUtil 中 public static final String RootPath = "G://upload/";  
4.修改数据库文件信息 persistence.xml  
5.部署即可


