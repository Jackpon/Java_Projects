//Java File Operation
Scanner in = new Scanner(System.in);

/*需求： 
	1.目录下的文件/文件夹判断？
	2.文件类型筛选
*/
	//1:
	File f = new File("C:/java/hello");
	f.list();
	f.isDirectory() //判断

	//目录：
		f.mkdir();//方法创建一个文件夹
		f.mkdirs();//方法创建一个文件夹和它的所有父文件夹。
		f.delete();

		
	//2:
	String filepath = "src\\Test1\\data\\";
	File file = new File(filepath);
	File[] filelist = file.listFiles(new MyFilenameFilter());
	class MyFilenameFilter implements FilenameFilter {
    @Override
    //重写accept方法,测试指定文件是否应该包含在某一文件列表中
    public boolean accept(File dir, String name) {
        // 创建返回值
        boolean flag = true;
        // 定义筛选条件
        //endWith(String str);判断是否是以指定格式结尾的
        if (name.toLowerCase().endsWith(".java")) {
        	
        } else {
            flag = false;
        }
        return flag;
    }
 
}