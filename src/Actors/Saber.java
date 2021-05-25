package Actors;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.IntRect;

import GameDriver.staticVariable;
import GameDriver.Musiccall;


/*
 * @para state: 闂備浇娉曢崰鎰板几婵犳艾绠柣鎴ｅГ閺呮悂鏌￠崒妯猴拷鏍拷姘秺閹虫繈寮婚妷锔芥闂佸搫鍊堕崐鏍拷姘秺瀵噣宕奸弴鐔告闂佸搫鍊堕崐鏍拷姘秺閹晠鎳滅喊澶嬪濞撴熬鎷烽柡浣规崌瀵剟濡堕崱妤婁紦闂備浇娉曢崰鎰板几婵犳艾绠柧姘�婚閬嶆⒑鐠恒劌鏋戦柡瀣煼楠炲繘鎮滈懞銉︽闂佸搫顦版竟鍡涖�傞锟介獮蹇旑槹鎼粹寬锕傛煙椤戣法绐旈柡浣规崌瀵剟濡堕崱妤婁紦闂佺儵鏅╅崰鏍ㄦ櫠閻樼粯鐓ラ柣鏂挎啞閻忣噣鏌熸搴″幋闁轰焦鎹囧顒勫Χ閸℃浼撻梻浣芥硶閸犳劙寮告繝姘闁绘垼濮ら弲鎼佹煟椤撗冧簽闁靛棗顑夐獮蹇涙倻閼恒儲娅㈤梺鍝勫�堕崐鏍拷姘秺瀹曟劖绺介崨濠冩闂佸搫鍊堕崐鏍拷姘辩暉tanding, moving, attacking, underattacking
 * 闂備浇娉曢崰鏇€�佸鍫濈疅閻庯綆鍋勯鍫曟⒑鐠恒劌鏋旂紒顔芥倐楠炴﹢宕ㄩ纰变紦闂備浇娉曢崰鎾剁懅闂佽鍎抽幊蹇曪拷姘秺閺屻劑鎮㈤崨濠勪紕闂佸綊顥撻崗姗�寮幘璇叉闁靛牆妫楅鍫曟偣閹伴潧浜伴柡浣规崌瀵剟濡堕崱妤婁紦闂備浇娉曢崰鎰板几婵犳艾绠柣鎴ｅГ閺呮悂鏌￠崒妯猴拷鏍拷姘秺閺屻劑鎮㈤崨濠勪紕闂佸綊顥撻崗姗�寮幘璇叉闁靛牆妫楅鍫曟⒑鐠恒劌鏋戦柡瀣煼楠炲繘鎮滈懞銉︽闂佸搫鍊堕崐鏍拷姘秺閺屻劑鎮ら崒娑橆伓
 * @para movingState: 闂備浇娉曢崰鎰板几婵犳艾绠柣鎴ｅГ閺呮悂鏌￠崒妯猴拷鏍拷姘秺閺屻劑鎮㈤崨濠勪紕闂佸綊顥撻崗姗�寮幘璇叉闁靛牆妫楅鍫曟⒑鐠恒劌鏋旈悗姘〒閹峰鎲撮崟顐紦
 * @para isSet 闂備浇娉曢崰鏇綖濡ゅ懎绀勯柕鍫濇椤忓爼姊虹捄銊ユ瀾闁哄顭烽獮蹇涙倻閼恒儲娅㈤梺鍝勫�堕崐鏍拷姘秺瀹曞爼宕滆椤ｏ拷->缂佺虎鍙庨崳锝夊极閹捐妫橀柕鍫濇椤忚埖鎱ㄩ敐鍛闁轰焦鎹囧顒勫Χ閸℃浼撻悗鐢稿亰娴滎亪寮幘璇叉闁靛牆妫楅鍫曟煛閸愩劎鍩ｉ柡浣规崌瀵剟濡堕崱妤婁紦闂佸憡鐟禍锝夈�呴敃鍌涚叆闁绘柨鎲￠悘顕�鏌熸搴″幋闁轰焦鎹囬弻濠囧垂椤愶絾婢掗柣銏╁灱閸犳锟芥艾缍婇弻銊╂偄閸涘﹦浼勯梺褰掝棑閸忔﹢寮幘璇叉闁靛牆妫楅鍫曟⒑鐠恒劌鏋戦柡瀣煼楠炲繘鎮块婊勵潔闂備浇娉曢崰鎰板几婵犳艾绠柣鎴ｅГ閺呮悂鏌￠崒妯猴拷鏍拷姘愁潐瀵板嫰骞橀鑺ユ闂佸搫鍊堕崐鏍拷姘愁潐閹便劑鏁撻敓锟�
 * @para sleepTime: 闂備浇娉曢崰鏇€�佸鍫濈闁归偊鍣弳婊堟煙閻戠瓔妫戞繛纰卞灦閺屻劑鎮㈤崨濠勪紕闂佸湱鍏橀弨鍗烆渻閸岀偞鐓ラ柣鏂挎啞閻忣噣鏌熼崙銈嗗
 * @notice: 婵帗绋掗敃銏ゅ极閹捐妫橀柕鍫濇椤忓爼鏌涢妷銉モ挃濠⒀勭墵閺屻劑鎮㈤崨濠勪紕闂佸綊顥撻崗姗�寮幘缁樺剹妞ゆ劑鍊ら弳婊堟煙妞嬪骸鍘撮柡浣规崌瀵剟濡堕崱妤婁紦ready闂備浇娉曢崰鎰板几婵犳艾绠柣鎴ｅГ閺呮悂鏌￠崒妯猴拷鏍拷姘秺瀹曞爼宕滆椤ｅ姊虹捄銊ユ瀾闁哄顭烽獮蹇涙晸閿燂拷
 */

public class Saber extends Actors implements Runnable
{
	private Thread thread;
	private float sleepTime;
	Musiccall musica=new Musiccall();
	public boolean isMagicExist;
	private boolean isDeadFinish = false;

	public Saber()
	{
		//闂備礁鎲＄敮鍥磹閺嶎厼钃熼柛銉墮缁狀噣鏌ㄩ悢鍓佺煓闁硅櫕顨婇幊鐐哄Ψ锜洪敃鍌涚厱婵ê澧介悾閬嶆煟鎺抽崝鎴濈暦閻戣姤鏅搁柨鐕傛嫹
		isMagicExist=false;
		//Index of saber
		this.indexX = 9;
		this.indexY = 0;
		//The size of images.
		this.width = 400;
		this.height = 400;
		//闂備胶灏ㄩ幏鐑芥偣閸ャ劌绲绘慨锝咃躬閺屻倝鎮℃惔鈩冩濠电偠顕滈幏锟�
		this.floatRectAttack= new FloatRect(x-100,y-50,200,100);
		//濠电偛顕鏇㈠磹閺囥埄鏁婂┑鐘叉处閸ゆ垿鏌涢幇鈺佸缂佹拝鎷�
		this.floatRectHarm= new FloatRect(x-80,y-50,160,100);
		//The order and other basic attributions
		this.order = null;
		this.isSet = false;
		this.direction = "right";
		this.attackCount = -1;
		this.attackNumber = 6;
		this.heroType = "Saber";
		//Primary action: right, ready
		this.shownPic = staticVariable.HeroReady.get(1);	
		this.maxX = shownPic.getSize().x;
		this.maxY = shownPic.getSize().y;
		this.container.setTexture(this.shownPic);
		this.container.setTextureRect(new IntRect(width*indexX, height*indexY, width, height));
		//Set the center of the character.
		this.container.setOrigin(200, 250);
		//the initial level,hp ,mana,damage of hero
	    this.level=1;
		this.exp=0;
		this.hp=150;
		this.mana=100;		
		this.hpHero=150;
		this.manaHero=100;
		this.m=5;
		this.n=10;
		this.p=10;
		this.t=3;
		this.s=skillNumber;
		this.damageHero=25;
		this.damageHeroSkill1=30;
		this.damageHeroSkill2=35;
		this.damageHeroSkill3=45;
		this.damageHeroSkill4=50;
		thread = new Thread(this);
		thread.start();
	}
	
	//The main controller of saber
	public void controller() 
	{
		this.floatRectAttack= new FloatRect(x-100,y-50,200,100);
		//濠电偛顕鏇㈠磹閺囥埄鏁婂┑鐘叉处閸ゆ垿鏌涢幇鈺佸缂佹拝鎷�
		this.floatRectHarm= new FloatRect(x-80,y-50,160,100);
		if(this.isDead)
			this.state = "dead";
		else if(!this.isDead)
		{
			if(this.order != null)
			{
				move(this.order);
				attack(this.order);
				skill(this.order);
			}
		}
		this.setMusic();
		this.setImages();
		this.changeIndex();
		this.container.setTexture(this.shownPic);
		this.container.setTextureRect(new IntRect(width*indexX, height*indexY, width, height));
	}
	
	   
	public void move(String order)
	{
		if((this.up || this.down || this.left || this.right) && !isAttack && this.state != "attacking")
		{
			this.state = "moving";
			moveX(order);
			moveY(order);
		}
		//If the character is not moving, attacking and using skills, then he/she becomes idle.
		if(order.equals("standing") && !this.up && !this.down && !this.left && !this.right && !isAttack && !isSkill)
		{
			this.heroIdle();
			musica.run.stop();
		}
	}
	
	//Moving along x axis. In x axis, the character can change the direction.
	public void moveX(String movingState)
	{
		if((movingState.equals("left_moving") || movingState.equals("right_moving")) 
				&& (this.left || this.right))
			this.direction = movingState.split("_")[0];
//		if(this.left == true && this.right == false)
//			this.x -= 4;
//		else if(this.right == true && this.left == false)
//			this.x += 4;
	}

	public void moveY(String movingState) 
	{
//		if(this.up)
//			this.y -= 4;
//		else if(this.down)
//			this.y += 4;
	}
    //add music
	public void setMusic() {
		if(!isSet)
		{
			if((this.right||this.left||this.up||this.down)&&(!this.isAttack)&&(!this.isSkill)&&this.state=="moving")
			{
				musica.run.play();
			}

			else if(!this.down && !this.up && !this.left && !this.right && !this.isSkill && this.isAttack)
			{
				musica.Saber_sound.play();
				musica.Saber_attack.play();
			}
			else if(!this.up && !this.down && !this.left && !this.right && !this.isAttack && this.isSkill)
			{
				if(this.skillNumber == 1)
				{
					Blood.Attack_Enemy(damageHeroSkill1);
					musica.Saber_skill1.play();
				}
				else if(this.skillNumber == 2)
				{
					Blood.Attack_Enemy(damageHeroSkill2);
					musica.Saber_skill2.play();
				}
				else if(this.skillNumber == 3)
				{
					Blood.Attack_Enemy(damageHeroSkill3);
					musica.Saber_skill3.play();
				}
				else if(this.skillNumber == 4)
				{
					Blood.Attack_Enemy(damageHeroSkill4);
					musica.Saber_skill4.play();
				}
			}else if(this.isDead) {
				musica.Saber_died.play();
			}
		}
	}

	//Add skill effects
	public void effect()
	{
		if(skillNumber==1&&indexY>1){
			if(!isMagicExist) {
				allMagic.add(new Magic(700,700));
				allMagic.get(0).type="saber";
				allMagic.get(0).skillNumber=1;
				allMagic.get(0).direction=this.direction;
				if(direction.equals("left"))
				{
					allMagic.get(0).container.setPosition(400,300);
				}
				else if(direction.equals("right"))
				{
					allMagic.get(0).container.setPosition(460,300);
				}
				isMagicExist = true;
			}
			else
			{
				if (allMagic.size() != 0) {
					if (allMagic.get(0).isFinish) {
						//System.out.println("闂傚倷绀佺亸鍛村箯闁垮鍙忛柨婵嗙箲鐎氾拷");
						allMagic.get(0).direction = "";
						allMagic.clear();
					}
				}
			}
		}
		if(skillNumber==2&&indexY>1){
			if(!isMagicExist) {
				allMagic.add(new Magic(512,512));
				allMagic.get(0).type="saber";
				allMagic.get(0).skillNumber=2;
				if(direction.equals("left"))
				{
					allMagic.get(0).container.setPosition(250,300);
				}
				else if(direction.equals("right"))
				{
					allMagic.get(0).container.setPosition(800,300);
				}
				allMagic.get(0).direction=this.direction;
				isMagicExist = true;
			}
			else {
				if (allMagic.size() != 0) {
					if (allMagic.get(0).isFinish) {
						//System.out.println("闂傚倷绀佺亸鍛村箯闁垮鍙忛柨婵嗙箲鐎氾拷");
						allMagic.get(0).direction = "";
						allMagic.clear();
					}
				}
			}
		}
		if(skillNumber==3&&indexY>=1){
			if(!isMagicExist) {
				allMagic.add(new Magic(800,600));
				allMagic.get(0).type="saber";
				allMagic.get(0).skillNumber=3;
				if(direction.equals("left"))
				{
					allMagic.get(0).container.setPosition(400,300);
				}
				else if(direction.equals("right"))
				{
					allMagic.get(0).container.setPosition(400,300);
				}
				allMagic.get(0).direction=this.direction;
				isMagicExist = true;
			}
			else {
				if (allMagic.size() != 0) {
					if (allMagic.get(0).isFinish) {
						//System.out.println("闂傚倷绀佺亸鍛村箯闁垮鍙忛柨婵嗙箲鐎氾拷");
						allMagic.get(0).direction = "";
						allMagic.clear();
					}
				}
			}
		}
		if(skillNumber==4&&indexY>2){
			if(!isMagicExist) {
				allMagic.add(new Magic(600,600));
				allMagic.get(0).type="saber";
				allMagic.get(0).skillNumber=4;
				if(direction.equals("left"))
				{
					allMagic.get(0).container.setPosition(400,350);
				}
				else if(direction.equals("right"))
				{
					allMagic.get(0).container.setPosition(550,350);
				}
				allMagic.get(0).direction=this.direction;
				isMagicExist = true;
			}
			else {
				if (allMagic.size() != 0) {
					if (allMagic.get(0).isFinish) {
						//System.out.println("闂傚倷绀佺亸鍛村箯闁垮鍙忛柨婵嗙箲鐎氾拷");
						allMagic.get(0).direction = "";
						allMagic.clear();
					}
				}
			}
		}
	}
	
	//Start and stop attacking.
	public void attack(String attackState)
	{
		//Start to attack
		if(isAttack && attackState.equals("attack_normal"))
			this.state = "attacking";
		//Decide whether stop attack or not. 
		if(isAttackFinish && !isAttack && this.state == "attacking")
			this.heroIdle();
	}
	
	//Use the skill, and the end of skill.
	public void skill(String skillState)
	{
		if(skillState.equals("skill") && this.isSkill && this.skillNumber != 0)
			this.state = "skilling";
		if(isSkillFinish && !isSkill && this.state == "skilling")
			this.heroIdle();
	}

	/*
	Change the index of the texture
	Some of the actions may have different ending-index
	So need to consider them correctly.
	 */
	public void changeIndex()
	{
		if(this.direction.equals("left"))
		{
			effect();
			indexX++;

			if(isDead && this.indexX == 9 && this.indexY == this.maxY / this.height - 1)
			{
				indexX--;
				indexY = indexY;
				isDeadFinish = true;
			}

			if(indexX == this.maxX / this.width)
			{
				indexX = 0;
				indexY++;
			}
			if(indexY ==  this.maxY / this.height - 1)
			{
				if(indexX == 8 && this.state == "standing")
					indexY++;
				//闂備浇娉曢崰搴ゃ亹閵婏箑顕辨繛鍡樺姇椤忓爼姊虹捄銊ユ瀾闁哄顭烽獮蹇涙倻閺傘倖瀚规慨妯垮煐閺呮悂鏌￠崒妯猴拷鏍拷姘秺閺屻劑鎮㈤崨濠勪紕闂佸綊顥撻崗姗�寮幘璇叉闁靛牆妫楅鍫曟煕閵夈儱鈷斿褝鎷�
				if(isAttack && !isAttackFinish && !isSkill)
				{
					if((indexX == 9 && this.attackCount == 0) ||
							(indexX == 8 && (this.attackCount == 1 || this.attackCount == 2)) ||
							(indexX == 4 && (this.attackCount == 3 || this.attackCount == 4 || this.attackCount == 5)))
					{
						indexY++;
						isAttackFinish = true;
						isAttack = false;
						isSet = false;
					}
				}
				
				//闂備浇娉曢崰搴ゃ亹閵婏箑顕辨繛鍡樺姇椤忓爼姊虹捄銊ユ瀾闁哄顭烽獮蹇涙倻閼恒儲娅㈤梺鍝勵槹婢瑰棗鈻嶉姀銈呯闁绘垼濮ら弲鎼佹煛閸屾ê锟芥牜锟芥艾缍婇弻銊╂偄閸涘﹦浼勯梺鐟板槻閸㈡彃霉濮楋拷閹囨晸閿燂拷
				if(isSkill && !isSkillFinish && !isAttack)
				{
					if((indexX == 7 && this.skillNumber == 1) ||
							(indexX == 1 && (this.skillNumber == 2 || this.skillNumber == 4)) ||
							(indexX == 5 && this.skillNumber == 3))
					{
						indexY++;
						isSkillFinish = true;
						isSkill = false;
						isSet = false;
						skillNumber = 0;
					}
				}
			}
			
			//End of the frame, then begin from start again.
			if(indexY == this.maxY / this.height)
			{
				indexY = 0;
				indexX = 0;
			}
			
		}
		else if(this.direction.equals("right"))
		{
			effect();
			indexX--;

			if(isDead && this.indexX == 0 && this.indexY == this.maxY / this.height - 1)
			{
				indexX++;
				indexY = indexY;
				isDeadFinish = true;
			}

			if(indexX == -1)
			{
				indexX = 9;
				indexY++;
			}
			
			if(indexY == this.maxY / this.height - 1)
			{
				if(indexX == 1 && this.state == "standing")
					indexY++;
				//闂備浇娉曢崰搴ゃ亹閵婏箑顕辨繛鍡樺姇椤忓爼姊虹捄銊ユ瀾闁哄顭烽獮蹇涙倻閼恒儲娅㈤梺鍝勫�堕崐鏍拷姘秺瀹曞爼宕滆椤ｏ拷
				if(isAttack && !isAttackFinish && !isSkill)
				{
					if((indexX == 0 && this.attackCount == 0) ||
							(indexX == 1 && (this.attackCount == 1 || this.attackCount == 2)) ||
							(indexX == 5 && (this.attackCount == 3 || this.attackCount == 4 || this.attackCount == 5)))
					{
						indexY++;
						isAttackFinish = true;
						isAttack = false;
						isSet = false;
					}
				}
				
				if(isSkill && !isSkillFinish && !isAttack)
				{
					if((indexX == 2 && this.skillNumber == 1) ||
							(indexX == 8 && (this.skillNumber == 2 || this.skillNumber == 4)) ||
							(indexX == 4 && this.skillNumber == 3))
					{
						indexY++;
						isSkillFinish = true;
						isSkill = false;
						isSet = false;
						skillNumber = 0;
					}
				}
			}
			
			if(indexY == this.maxY / this.height)
			{
				indexY = 0;
				indexX = 9;
			}
		}

		if(!isSkill)
		{
			isMagicExist = false;
			if (allMagic.size() != 0) {
				if (allMagic.get(0).isFinish) {
					System.out.println("闂傚倷绀佺亸鍛村箯闁垮鍙忛柨婵嗙箲鐎氾拷");
					allMagic.get(0).direction = "";
					allMagic.clear();
				}
			}
		}
	}
	
	@Override
	public void run() 
	{
		while(!isDeadFinish)
		{
			this.controller();
			if(this.state == "moving")
				this.sleepTime =  movespeed;
			else if(this.state == "standing")
				this.sleepTime = 50;
			else if(this.state == "attacking")
				this.sleepTime = attackspeed;
			else if(this.state == "skilling")
				this.sleepTime = skillspeed;
			else if(this.state == "dead")
				this.sleepTime = 50;
			try {
				Thread.sleep((long) sleepTime);
			} catch (InterruptedException e) {
			}
		}
	}
}
