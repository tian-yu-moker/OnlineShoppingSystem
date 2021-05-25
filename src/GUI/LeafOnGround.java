package GUI;

public class LeafOnGround
/*存储地面上的树叶的信息*/
{
	public LeafOnGround(int x1, int y1, int i1)
	{
		x=x1;
		y=y1;
		i=i1;
	}
	int x=0;//坐标
	int y=0;
	int i=0;//对应FlyingLeaves类drawFlyingLeaves方法for循环中的i
}
