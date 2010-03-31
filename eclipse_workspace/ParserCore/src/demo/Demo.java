package demo;

class Demo{
	public Demo(){
		
	}
	private String name;
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	private class MyInnerClass{
		public MyInnerClass(){
			//constructor
		}
		public void anInnerMethod(){
			
		}
	}
}