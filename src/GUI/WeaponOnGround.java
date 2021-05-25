package GUI;
import org.jsfml.graphics.RectangleShape;

public class WeaponOnGround 
/*此类的每个对象存储地面上的一件装备的空间参数
 * 2020.5.2
 * Backpack类依赖此类（用到了此类的数据类型，虚线箭头）*/
{
	WeaponOnGround(int x1,int y1,int width1,int height1,int Number1,RectangleShape rectangleShape1)
	{
		
		x=x1;
		y=y1;
		width=width1;
		height =height1;
		Number=Number1;
		rectangleShape=rectangleShape1;
		amount=1;//默认本装备数量为1
	}

	//重载
	WeaponOnGround(int Number1,int amount1,RectangleShape RectangleShape1)
	{
		Number=Number1;
		amount=amount1;
		rectangleShape=RectangleShape1;
	}
	
	int x=0;//装备的横坐标（图片的左上角；相对于jsfml窗口）
	int y=0;//装备的纵坐标
	int width=100;//装备图片的宽度
	int height=100;//装备图片的高度
	int Number;//装备的编号（与图片一一对应）
	RectangleShape rectangleShape=null;

	int amount=1;//本装备数量（背包中编号相同的田宇装备共用一个WeaponOnGround对象）

}
