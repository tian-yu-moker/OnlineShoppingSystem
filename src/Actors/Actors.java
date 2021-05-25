package Actors;


import GameDriver.staticVariable;
import org.jsfml.graphics.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Actors 
{
	public int x = 600; //Position x
	public int y = 300; //Position y

	public int range=200;

	public FloatRect floatRectAttack;

	public FloatRect floatRectHarm;

	public int indexX;
	public int indexY;

	public boolean isSet;
	public String direction;
	public int attackCount;
	public ConstTexture shownPic;
	public Sprite container = new Sprite();
	public static String heroType;

	//the level and exp of hero
	public static int level=1;
	public static int exp=0;  
	public static String l="1"; //the level string on the map
	public static  float totalHp;   
	public static float totalMana;
	public static  float totalDamage;   
	public static float totalSpeed;
	
	//initial hp,mana damage of hero
	public static  float hp;   
	public static float mana;
	public static float damageHero;
	public static float damageHeroSkill1;
    public static float damageHeroSkill2;
    public static float damageHeroSkill3;
    public  static float damageHeroSkill4;
	    
    public  static float m; //increase attack each level
	public static  float n;//increase hp each level
	public static  float p; //increase mana  each level
	public static float t;//the type number of hero
	public static float s;//the skill number of hero
		
	public static float equiDamage;  //increase Damage from equipment
	public static float equiHp;   //increase hp from equipment	
	public static float equiSpeed;  //increase speed from equipment
	public static float equiMana; //increase mana from equipment
	
	// the current hp, defense and mana of hero
	public static  float hpHero;	
	public static float armorHero;
	public static   float manaHero;
	// the hp, damage and defense of enrmy
	public  float hpEnemy;
	public  float damageEnemy;
	public  float armorEnemy;
		
	//闂傚倸鍊搁崐鎼佸磹瀹勬噴褰掑炊瑜夐弸鏍煛閸ャ儱鐏╃紒鎰殜閺岀喖鎮ч崼鐔哄嚒闂佸憡鍨规慨鎾煘閹达附鍋愰悗鍦Т椤ユ繄绱撴担鍝勵�岄柛銊ョ埣瀵鏁愭径濠勵吅闂佹寧绻傞幉娑㈠箻缂佹鍘搁梺鍛婁緱閸犳宕愰幇鐗堢厸鐎癸拷鐎ｎ剛鐦堥悗瑙勬礃鐢帟鐏掗梺缁樿壘閻°劎锟芥艾缍婇弻鈥愁吋鎼粹�插闂佺懓鍢查崲鏌ワ綖濠靛鏁嗛柛灞剧敖閵娾晜鈷戦柛婵嗗椤箓鏌涢弮锟介崹鍧楃嵁閸愵喖顫呴柕鍫濇噹缁愭稒绻濋悽闈浶㈤悗姘间簽濡叉劙寮撮姀鈾�鎷绘繛杈剧到閹芥粎绮旈悜妯镐簻闁靛闄勫畷宀�锟借娲橀〃鍛达綖濠婂牊鏅搁柦妯侯樈閸ゆ洘銇勯幇鍓佺暠缂佺姾宕电槐鎾存媴婵埈浜畷婊堫敇閵忊檧鎷虹紓渚囧灡濞叉﹢锝炴繝鍥ㄧ厱婵☆垰鎼埛鏃傜磼椤旇姤顥堢�规洖銈稿鎾偄閸濆嫬绠ユ繝纰夌磿閸嬫盯顢栭崨顕呮闁归棿绀侀崒銊╂煙缂併垹鏋熼柣鎾寸☉闇夐柨婵嗙墱濞兼劗锟借娲栭惉濂告晸閼恒儺鍟忛柛锝庡櫍瀹曟粓鎮㈤梹鎰畾闂佸壊鍋呭ú鏍嵁閵忊�茬箚闁靛牆鎷戝妤冪磼閹插鐣垫慨濠傤煼瀹曟帒鈻庨幇顔哄仒婵犵數鍋涢ˇ鏉棵洪悢椋庢殾濞村吋娼欑粻娑㈡煛婢跺﹦浠㈤柛銈嗗灦缁绘繈鎮介棃娴讹綁鏌ら悷鏉库挃缂侇喖顭烽、娑㈡倷鐎电骞楅梻浣侯攰閹活亞寰婇崐鐕佹毐闂傚倷绀侀幖顐﹀箠韫囨稑绠栭柛宀�鍋涢拑鐔兼煥閻斿搫孝缁炬儳鍚嬫穱濠囶敍濠婂啰娈ら梺褰掝棑閸忔ê顕ｆ禒瀣垫晝妞ゆ帊绀侀璺衡攽椤旀枻渚涢柛鎾寸〒缁柨煤椤忓懐鍘搁柣蹇曞仩椤曆勬叏閸岀偞鐓欐い鏂挎惈閻忚尙锟借娲忛崝宥囨崲濠靛绀嬫い蹇撴閿涚喖姊婚崒姘拷椋庣矆娴ｈ櫣绀婂┑鐘叉硽婢舵劕绠婚悹鍥皺椤ρ冣攽椤旂瓔娈ｉ柟鐑筋棑閿熻姤顔栭崳顕�宕戞繝鍥╁祦婵☆垰鍚嬬�氭岸鏌涘▎蹇ｆ▓婵☆偆鍠栧缁樼瑹閿熻棄顭囪閹囧幢濡炪垺绋戣灃闁告粈鐒﹂弲婊堟⒑閸撴彃浜濇繛鍙夛耿閸╂盯骞嬮敂钘変化闂佽鍘界敮鎺撲繆婵傚憡鐓涢悗锝庡亜閻忔挳鏌″畝瀣？闁跨喐鏋荤紞鍡樼閸洘鍋傞柛蹇曗拡濞堜粙鏌ｉ幇顖氱毢缂佺姴顭烽弻鐔碱敊閻ｅ瞼鐓夐梺鐟扮－閸嬨倖淇婇悜鑺ユ櫆缂佹稑顑勯幋鐑芥⒒閸屾艾锟介绮堟笟锟介獮澶愬灳濠㈠嫭绋栫粻娑樷槈濡》绱卞┑鐘灱閸╂牠宕濋敃鍌氭瀬濠电姴娲﹂悡娆撴煟濡わ拷閻楀﹦娆㈤懠顒傜＜闁绘瑱鎷烽柛銊ョ埣瀵濡搁埡鍌氫簽闂佺鏈粙鎴︻敂閿燂拷
	public int attack;
	public int attackSpeed;
	//the initial attack speed, move speed,and skill speed of hero
	public static float attackspeed=50;
	public static float movespeed=70;
	public static float skillspeed=50;

	public int width;
	public int height;
	
	public int maxX;
	public int maxY;
	
	public String order;
	public String state = "standing";
	public boolean isDead = false;
	
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	
	//闂傚倸鍊搁崐鎼佸磹瀹勬噴褰掑炊瑜夐弸鏍煛閸ャ儱鐏╃紒鎰殜閺岋繝宕堕懜鐢电獧缂傚倸绉甸悧妤佺┍婵犲洤围闁告侗鍠栧▍锝夋煟閻斿摜鎳曠紒鐘虫崌瀵濡搁埡浣虹潉闂佹悶鍎洪悘婵嬪箻缂佹鍘搁梺绯曞墲钃辩紒鑸电叀濡焦寰勯幇顓犲幈闂婎偄娲﹂懝鐐瑜版帗鐓欓柛鎰絻椤忣參鏌熼绛嬫當妞ゎ偅绻堥幃娆擃敆閿熶粙顢旈敓锟�
	public boolean isAttack = false;
	public boolean isRightAttack = false;
	public boolean isLeftAttack = false;
	public boolean isAttackFinish = false;//闂傚倸鍊搁崐鎼佸磹瀹勬噴褰掑炊瑜夐弸鏍煛閸ャ儱鐏╃紒鎰殜閺岋繝宕堕懜鐢电獧缂傚倸绉甸悧妤佺┍婵犲洤围闁告侗鍠栧▍锝夋煟閻斿摜鎳曠紒鐘虫崌瀵濡搁埡浣虹潉闂佹悶鍎洪悘婵嬪箻缂佹鍘搁梺绯曞墲钃辩紒鑸电叀濡焦寰勯幇顓犲幈闂婎偄娲﹂懝鐐瑜版帗鐓欓柛鎰絻椤忣參鏌熼绛嬫當妞ゎ偅绻堥、妤呭磼濠х偑鍔戝娲传閸曨噮娼堕梺鍛婃⒐閸ㄥ潡鐛崘顔碱潊闁靛牆鎳庣粣娑欑節閻㈤潧孝閻庢凹浜炲Σ鎰板即閵忊檧鎷绘繛杈剧到閹芥粎绮旈悜妯镐簻闁靛闄勫畷宀�锟借娲橀〃鍛达綖濠婂牊鏅搁柦妯侯樈閸ゆ洘銇勯幇鍓佺暠缂佺姴顭烽幃褰掑炊瑜嶇痪褎銇勯妷锔剧疄婵﹥妞藉Λ鍐ㄢ槈濮橆剦鏆繝纰樻閸嬪懘銆冩繝鍥ワ拷渚�寮崼鐔告闂佽法鍣﹂幏锟�
	public int attackNumber; //闂傚倸鍊搁崐鎼佸磹瀹勬噴褰掑炊瑜夐弸鏍煛閸ャ儱鐏╃紒鎰殜閺岀喖鎮ч崼鐔哄嚒闂佸憡鍨规慨鎾煘閹达附鍋愰悗鍦Т椤ユ繄绱撴担鍝勵�岄柛銊ョ埣瀵鏁愭径濠勵吅闂佹寧绻傞幉娑㈠箻缂佹鍘搁梺鍛婁緱閸犳宕愰幇鐗堢厸鐎癸拷鐎ｎ剛鐦堥悗瑙勬礃鐢帟鐏掗梺缁樿壘閻°劎锟芥艾缍婇弻鈥愁吋鎼粹�插闂佺懓鍢查崲鏌ワ綖濠靛鏁嗛柛灞剧敖閵娾晜鈷戦柛婵嗗椤箓鏌涢弮锟介崹鍧楃嵁閸愵喖顫呴柕鍫濇噹缁愭稒绻濋悽闈浶㈤悗姘间簽濡叉劙寮撮姀鈾�鎷绘繛杈剧到閹诧紕鎷归敓鐘崇厱闊洦妫戦懓鍧楁寠閻斿吋鐓欓柟瑙勫姉琚﹂梺缁樺灦閿氭い鏇憾閹鈽夊▎妯煎姺闂佸憡鐟ュΛ婵嗩潖閾忓湱纾兼俊顖濆吹閸欏棝姊虹涵鍜佸殝缂佺粯绻堥獮鍐潨閿熶粙銆佸鑸垫櫢濞寸姴顑呴拑鐔兼煟閺傚灝顥忔俊鎻掔秺楠炴牜鍒掗惂鍛婂闁告劘灏欓娲⒒閸屾瑦绁版俊妞煎姂閹偤鏁冮崒姘鳖唹闂佹悶鍎滅仦鑺ヮ吙婵＄偑鍊栫敮鎺旓拷姘煎墮濞插潡姊绘担铏广�婇柛鎾寸箞閵嗗啳绠涢弬娆惧殼闂佺懓澧界划顖炲煕閹烘嚚褰掓晲閸粳鎾剁棯椤撶偟鍩ｉ柡灞剧洴閹晠宕橀幓鎺濇綒闁诲孩顔栭崰鏍箟閿涘嫭宕叉繝闈涙－濞尖晜銇勯幒鎴濅簽闁哥偟鏁诲缁樻媴閼恒儳銆婇梺闈╃秶缁犳捇鐛箛娑欐櫢闁跨噦鎷�
	
	//闂傚倸鍊搁崐鎼佸磹瀹勬噴褰掑炊瑜夐弸鏍煛閸ャ儱鐏╃紒鎰殜閺岋繝宕堕懜鐢电獧缂傚倸绉甸悧妤佺┍婵犲洤围闁告侗鍠栧▍锝囩磽娓氬﹥瀚归梺鍛婃处閸ㄩ亶鎮¤箛娑欑厱妞ゆ劧绲跨粻鏍ㄣ亜閵夛妇鐭嬮柕鍥у缁犳盯骞樼捄铏瑰幗婵犳鍠栭敃銊モ枍閿濆绠查柛鏇ㄥ灠鎯熼梺闈涱檧婵″洦绂嶉鍫熲拻濞达絽鎲￠崯鐐烘煟閻曞倻鐣辨い顏勫暣閹稿﹥寰勫Ο鐑橆吙闂備線娼ц墝闁哄懏绋撶划鍫ュ醇閵忊�虫瀾闁诲函缍嗛崰妤呭疾閹间焦鐓㈡俊顖欒濡牓鏌涙惔銏♀拹妞ゃ劊鍎甸幃娆撴嚑椤掑偆鍟嬮梻浣筋嚃閸犳鍒掗幘璇茬疇闁绘劕鎼敮闂佹寧姊婚悺鏃�绂掕濮婂宕掑▎鎴М闂佸湱鈷堥崑濠傜暦閹版澘閱囬柡鍥╁仧閿涙瑩姊洪崫鍕枆闁稿鎹囬獮蹇撶暋闁附鍍甸梺缁樺姦閸撴瑩顢旈敓锟�
	public boolean isSkill = false;
	public boolean isSkillFinish = false; //闂傚倸鍊搁崐鎼佸磹瀹勬噴褰掑炊瑜夐弸鏍煛閸ャ儱鐏╃紒鎰殜閺岀喖鎮ч崼鐔哄嚒闂佸憡鍨规慨鎾煘閹达附鍋愰悗鍦Т椤ユ繄绱撴担鍝勵�岄柛銊ョ埣瀵鏁愭径濠勵吅闂佹寧绻傞幉娑㈠箻缂佹鍘搁梺鍛婁緱閸犳宕愰幇鐗堢厸鐎癸拷鐎ｎ剛鐦堥悗瑙勬礃鐢帟鐏掗梺缁樿壘閻°劎锟芥艾缍婇弻鈥愁吋鎼粹�插闂佺懓鍢查崲鏌ワ綖濠靛鏁嗛柛灞剧敖閵娾晜鈷戦柛婵嗗椤箓鏌涢弮锟介崹鍧楃嵁閸愵喖顫呴柣妯烘濞咃絿妲愰幒鎳崇喐娼悧鍫熲枙闂傚倸鍊搁崐椋庣矆娓氾拷楠炲鏁撻悩鎻掔�銈嗙墱閸嬫稓澹曡ぐ鎺撶厸鐎广儱楠告禍楣冩煙瀹勬澘妲绘い顐ｇ箞椤㈡宕掑┃鐐姂濮婃椽宕崟顕呮蕉闂佸憡姊归崹鍧楃嵁閸愵喖顫呴柕鍫濇娴滄鏌熼懝鐗堝涧缂佽鲸娲熷濂告晸娴犲鈷掗柛灞剧懆閸忓矂鎮樿箛瀣妤犵偛绻橀幃婊堟嚍閵壯冨箺闂備線锟芥稑宓嗘繛浣冲嫭娅犳い鏍仦閻撶喖鐓崶椋庡埌婵炲懎绉归弻鈥崇暆閿熶粙宕伴幇鍏洭鎮ч崼鐔峰妳闂佺偨鍎遍崯璺ㄧ不閿濆鈷掑ù锝堝Г閵嗗啴鏌ｉ幒鐐电暤妤犵偞鍨垮畷鎯邦檨闁搞倖娲橀妵鍕箳閹存繍浠奸柛銉︽尦濮婅櫣鍖栭弴鐐测拤濡炪們鍔岀换鎴狅拷闈涖偢閺佹捇鏁撻敓锟�
	public int skillNumber = 0; //闂傚倸鍊搁崐鎼佸磹瀹勬噴褰掑炊瑜夐弸鏍煛閸ャ儱鐏╃紒鎰殜閺岀喖鎮ч崼鐔哄嚒闂佸憡鍨规慨鎾煘閹达附鍋愰悗鍦Т椤ユ繄绱撴担鍝勵�岄柛銊ョ埣瀵鏁愭径濠勵吅闂佹寧绻傞幉娑㈠箻缂佹鍘搁梺鍛婁緱閸犳宕愰幇鐗堢厸閻忕偟顭堟晶鑼拷鍨緲鐎氼喗绂掗敂鍓ч┏閻庯綆鍋呴惁鏃堟⒒閸屾瑧顦﹂柟鑺ョ矒瀹曟垵鈽夐埗鈺嬫嫹閿曞倹鍊婚柣锝呰嫰缁侊箓妫呴銏″婵☆偄绻愬嵄鐟滅増甯掔粻鍦磼椤旀娼愭い顐攻缁绘稒绺介崫銉ф毇濠殿喖锕︾划顖炲箯閸涘瓨鍤嶉柕澹緤鎷锋繝鍥ㄢ拺闁告縿鍎辨牎闂佸憡姊归悷銉╊敋閿濆宸濆┑鐘插閻﹀牆鈹戦鏂や緵闁告挻鐩、娆撳幢濞戞瑢鎷虹紓鍌欑劍閿氶柣蹇撳船閳规垿顢欓崫鍕舵嫹濠靛棭鍤曢柡灞诲劚娴肩娀鏌曟竟顖氭嫅缁卞啿鈹戦悙鑸靛涧缂佽弓绮欓獮鏍敃閿濆浂娴勯悷婊勬瀵寮撮姀鐘茶�垮┑掳鍊曢敃銈夊礈鐠轰綍鏃堟偐闂堟稐绮剁紓浣虹帛閿曘垽鐛崘顭戠叆闁割偆鍠庡▓鐔兼⒑闂堟侗妾ч梻鍕椤㈡棃鎮㈤崗灏栨嫼闂佸憡绻傜�氬嘲危濞差亝鐓曢悗锝庡亞閵嗘帒霉濠婂嫭鍊愮�规洏鍔戝鍫曞箣閻欙拷閸炵敻姊绘担鑺ョ《闁革綇绠撻獮蹇涙晸閿燂拷0闂傚倸鍊搁崐鎼佸磹瀹勬噴褰掑炊瑜夐弸鏍煛閸ャ儱鐏╃紒鎰殜閺岀喖鎮ч崼鐔哄嚒闂佸憡鍨规慨鎾煘閹达附鍋愰悗鍦Т椤ユ繄绱撴担鍝勵�岄柛銊ョ埣瀵鏁愭径濠勵吅闂佹寧绻傞幉娑㈠箻缂佹鍘搁梺鍛婁緱閸犳宕愰幇鐗堢厸鐎癸拷鐎ｎ剛鐦堥悗瑙勬礃鐢帟鐏掗梺缁樿壘閻°劎锟芥艾缍婇弻鈥愁吋鎼粹�插闂佺懓鍢查崲鏌ワ綖濠靛洦缍囬柕濞垮劜閻ｎ剚淇婇悙顏勶拷鏍洪埡鍐闁跨喓鏅槐鎺楁偐瀹曞洠妲堥梺瀹犳椤︻垶鍩㈡惔銊ョ疀妞ゆ挆鍌涘煕闂傚倸鍊搁崐椋庣矆娓氾拷楠炴牠顢曢敃锟界壕褰掓煕椤愶絾绀�缁炬儳缍婇弻鈥愁吋鎼粹�插闂佺懓鍢查崲鏌ワ綖濠靛鏁嗛柛灞剧敖閵娾晜鈷戦柛婵嗗椤箓鏌涢弮锟介崹鍧楃嵁閸愵喖顫呴柕蹇婏拷宕囧酱婵犵數鍋炲娆擃敄閸℃稒鍊靛┑鍌氭啞閳锋帒霉閿濆牆袚缁绢厼鐖奸弻娑㈡晲韫囨洜鏆ゅΔ鐘靛仜缁绘﹢寮幘缁樻櫢闁跨噦鎷�1闂傚倸鍊搁崐鎼佸磹瀹勬噴褰掑炊瑜夐弸鏍煛閸ャ儱鐏╃紒鎰殜閺岀喖鎮ч崼鐔哄嚒闂佸憡鍨规慨鎾煘閹达附鍋愰悗鍦Т椤ユ繄绱撴担鍝勵�岄柛銊ョ埣瀵濡搁埡鍌氫簽闂佺鏈粙鎴︻敂閿燂拷2闂傚倸鍊搁崐鎼佸磹瀹勬噴褰掑炊瑜夐弸鏍煛閸ャ儱鐏╃紒鎰殜閺岀喖鎮ч崼鐔哄嚒闂佸憡鍨规慨鎾煘閹达附鍋愰悗鍦Т椤ユ繄绱撴担鍝勵�岄柛銊ョ埣瀵濡搁埡鍌氫簽闂佺鏈粙鎴︻敂閿燂拷3闂傚倸鍊搁崐鎼佸磹瀹勬噴褰掑炊瑜夐弸鏍煛閸ャ儱鐏╃紒鎰殜閺岀喖鎮ч崼鐔哄嚒闂佸憡鍨规慨鎾煘閹达附鍋愰悗鍦Т椤ユ繄绱撴担鍝勵�岄柛銊ョ埣瀵濡搁埡鍌氫簽闂佺鏈粙鎴︻敂閿燂拷4

	public List<Magic> allMagic=new ArrayList<Magic>();
	
	//闂傚倸鍊搁崐鎼佸磹瀹勬噴褰掑炊瑜夐弸鏍煛閸ャ儱鐏╃紒鎰殜閺岀喖鎮ч崼鐔哄嚒闂佸憡鍨规慨鎾煘閹达附鍋愰悗鍦Т椤ユ繄绱撴担鍝勵�岄柛銊ョ埣瀵鏁愭径濠勵吅闂佹寧绻傞幉娑㈠箻缂佹鍘搁梺鍛婁緱閸犳宕愰幇鐗堢厸鐎癸拷鐎ｎ剛鐦堥悗瑙勬礃鐢帟鐏掗梺缁樿壘閻°劎锟芥艾缍婇弻鈥愁吋鎼粹�插闂佺懓鍢查崲鏌ワ綖濠靛鏁嗛柛灞剧敖閵娾晜鈷戦柛婵嗗椤箓鏌涢弮锟介崹鍧楃嵁閸愵喖顫呴柕鍫濇噹缁愭稒绻濋悽闈浶㈤悗姘间簽濡叉劙寮撮姀鈾�鎷绘繛杈剧到閹芥粎绮旈悜妯镐簻闁靛闄勫畷宀�锟借娲橀〃鍛达綖濠婂牊鏅搁柦妯侯樈閸ゆ洘銇勯幇鍓佺暠缂佺姴顭烽弻銈夊箹娴ｈ閿梺鍝勬娴滆泛顫忛搹鍦＜婵☆垵宕甸崣鍡涙⒑绾拋鍤嬬紒缁樼箞楠炲啴鍨鹃弬銉︾�婚梺瑙勫劤椤曨參宕㈤柆宥嗏拺闁荤喐澹嗛幗鐘绘煟閻旀潙鍔﹂柡浣规崌閹崇偤濡疯閳峰牓姊虹捄銊ユ灆濠殿喓鍊濋幃銏ゎ敆閸曨偆顔嗛梺璺ㄥ櫐閹凤拷
	public abstract void moveX(String state);
	public abstract void moveY(String state);
	public abstract void controller();
	
	//the value of level 
	public static void level() {		
		if(level==2||level==3||level==4||level==5||level==6||level==7||level==8||level==9||level==10
				||level==11||level==12||level==13||level==14||level==15||level==16||level==17||level==18||level==19||level==20) {
			 hp+=1*n;	//total hp of hero
			 mana+=1*p;		 //total mana of hero
			 damageHero+=1*m;			
			 damageHeroSkill1+=1*(t+s);
			 damageHeroSkill2+=1*(t+s);
			 damageHeroSkill3+=1*(t+s);
			 damageHeroSkill4+=1*(t+s);	
			//System.out.println(damageHero);
			attackspeed -= 0.5;
			movespeed -= 1;
			skillspeed -= 0.5;
			//System.out.println("the movespeed is"+movespeed);		
		}
			if(level==2) {
				l="2";			
			}else if(level==3) {
				l="3";			
			}else if(level==4) {
				l="4";			
			}else if(level==5) {
				l="5";			
			}else if(level==6) {
				l="6";			
			}else if(level==7) {
				l="7";			
			}else if(level==8) {
				l="8";			
			}else if(level==9) {
				l="9";			
			}else if(level==10) {
				l="10";			
			}else if(level==11) {
				l="11";			
			}else if(level==12) {
				l="12";			
			}else if(level==13) {
				l="13";			
			}else if(level==14) {
				l="14";			
			}else if(level==15) {
				l="15";			
			}else if(level==16) {
				l="16";			
			}else if(level==17) {
				l="17";			
			}else if(level==18) {
				l="18";			
			}else if(level==19) {
				l="19";			
			}else if(level==20) {
				l="20";			
			}
		}
		
		

	public void setImages()
	{
		if(this.right && this.shownPic.equals(staticVariable.HeroRun.get(0)))
			isSet = false;
		if(this.left && this.shownPic.equals(staticVariable.HeroRun.get(1)))
			isSet = false;

		if(isDead)
		{
			if(this.direction.equals("left"))
			{
				this.shownPic = staticVariable.HeroDead.get(0);
				isSet = true;
			}
			else if(this.direction.equals("right"))
			{
				this.shownPic = staticVariable.HeroDead.get(1);
				isSet = true;
			}
		}

		if(!isSet)
		{
			if(!isDead)
			{
			//When the actor is moving, and the direction is right.
				if(this.right && !this.left && this.state == "moving" && this.direction.equals("right"))
				{
					this.shownPic = staticVariable.HeroRun.get(1);
					isSet = true;
				}
				else if(this.left && !this.right && this.state == "moving" && this.direction.equals("left"))
				{
					this.shownPic = staticVariable.HeroRun.get(0);
					isSet = true;
				}
				if((this.up || this.down) && this.direction.equals("right"))
				{
					this.shownPic = staticVariable.HeroRun.get(1);
					isSet = true;
				}
				else if((this.up || this.down) && this.direction.equals("left"))
				{
					this.shownPic = staticVariable.HeroRun.get(0);
					isSet = true;
				}
				//When the actor does not move, and is attacking now.
				if(!this.down && !this.up && !this.left && !this.right && !this.isSkill && this.isAttack)
				{
					attackCount++;
					if(attackCount == attackNumber)
					{
						attackCount = -1;
						attackCount++;
					}
					if(this.direction.equals("left"))
					{
						this.shownPic = staticVariable.HeroAttackLeft.get(this.attackCount);
						isSet = true;
						isAttackFinish = false;
					}
					else if(this.direction.equals("right"))
					{
						this.shownPic = staticVariable.HeroAttackRight.get(this.attackCount);
						isSet = true;
						isAttackFinish = false;
					}
				}
				//When this actor does not move, normal attack, and is using skill now.
				else if(!this.up && !this.down && !this.left && !this.right && !this.isAttack && this.isSkill)
				{
					//If the first skill is selected.
					if(this.skillNumber == 1)
					{
						//Left or right
						if(this.direction.equals("left"))
						{
							this.shownPic = staticVariable.HeroSkill1.get(0);
							isSet = true;
							isSkillFinish = false;
						}
						else if(this.direction.equals("right"))
						{
							this.shownPic = staticVariable.HeroSkill1.get(1);
							isSet = true;
							isSkillFinish = false;
						}
					}
					else if(this.skillNumber == 2)
					{
						//Left or right
						if(this.direction.equals("left"))
						{
							this.shownPic = staticVariable.HeroSkill2.get(0);
							isSet = true;
							isSkillFinish = false;
						}
						else if(this.direction.equals("right"))
						{
							this.shownPic = staticVariable.HeroSkill2.get(1);
							isSet = true;
							isSkillFinish = false;
						}
					}
					else if(this.skillNumber == 3)
					{
						//Left or right
						if(this.direction.equals("left"))
						{
							this.shownPic = staticVariable.HeroSkill3.get(0);
							isSet = true;
							isSkillFinish = false;
						}
						else if(this.direction.equals("right"))
						{
							this.shownPic = staticVariable.HeroSkill3.get(1);
							isSet = true;
							isSkillFinish = false;
						}
					}
					else if(this.skillNumber == 4)
					{
						//Left or right
						if(this.direction.equals("left"))
						{
							this.shownPic = staticVariable.HeroSkill4.get(0);
							isSet = true;
							isSkillFinish = false;
						}
						else if(this.direction.equals("right"))
						{
							this.shownPic = staticVariable.HeroSkill4.get(1);
							isSet = true;
							isSkillFinish = false;
						}
					}
				}
			}


			if(isSet)
			{
				if(this.direction.equals("left"))
				{
					this.indexX = 0;
					this.indexY = 0;
				}
				else if(this.direction.equals("right"))
				{
					if(this.heroType.equals("Saber") || this.heroType.equals("Archer"))
					{
						this.indexX = 9;
						this.indexY = 0;
					}
					else if(this.heroType.equals("Knight") || this.heroType.equals("Lancer"))
					{
						this.indexX = 7;
						this.indexY = 0;
					}
				}
			}
		}
		this.maxX = shownPic.getSize().x;
		this.maxY = shownPic.getSize().y;
	}

	public void heroIdle()
	{
		//闂傚倸鍊搁崐鎼佸磹瀹勬噴褰掑炊瑜夐弸鏍煛閸ャ儱鐏╃紒鎰殜閺岀喖鎮ч崼鐔哄嚒闂佸憡鍨规慨鎾煘閹达附鍋愰悗鍦Т椤ユ繄绱撴担鍝勵�岄柛銊ョ埣瀵鏁愭径濠勵吅闂佹寧绻傞幉娑㈠箻缂佹鍘搁梺鍛婁緱閸犳宕愰幇鐗堢厸鐎癸拷鐎ｎ剛鐦堥悗瑙勬礃鐢帟鐏掗梺缁樿壘閻°劎锟芥艾缍婇弻鈥愁吋鎼粹�插闂佺懓鍢查崲鏌ワ綖濠靛鏁嗛柛灞剧敖閵娾晜鈷戦柛婵嗗椤箓鏌涢弮锟介崹鍧楃嵁閸愵喖顫呴柕鍫濇噸缁卞爼姊洪崨濠冨闁稿妫濇俊鍫曨敊閹存帞绠氶梺缁樺姦娴滄粓鏁撻懞銉у⒌鐎规洘绻堥弫鍐磼濮橀硸妲舵繝娈垮枟钃遍柛鐐差煼楠炲繐鐣￠柇锔藉兊濡炪倖甯掗崐缁橆殭闂傚倷绀侀幖顐ゆ偖椤愶箑绀夐柟杈剧畱缁犳牠鏌曡箛瀣拷鏇烆啅濠靛棌鏀介柣妯诲絻椤徰呯磼鏉堛劍绀嬫慨濠勭帛閹峰懘宕妷顬劌顪冮妶鍐ㄥ姕闁圭懓娲ら悾宄拔旈敓浠嬶綖濠婂牊鏅搁柦妯侯樈閸ゆ洘銇勯幇鍓佺暠缂佺姴顭烽幃褰掑炊瑜嶇痪褎銇勯妷锔剧疄婵﹥妞藉Λ鍐ㄢ槈濮橆剦鏆繝纰樻閸嬪懘銆冩繝鍥ワ拷渚�寮崼婵嬪敹闂佸搫娲ㄩ崰搴㈢椤栫偞鈷戦梺顐ｇ☉瀹撳棙绻涙担鍐插椤╂彃霉閻撳海鎽犻柣鎾寸☉闇夐柨婵嗙墱濞兼劗锟借娲栭惉濂告晸閼恒儺鍟忛柛锝庡櫍瀹曟粓鎮㈤梹鎰畾闂佸壊鍋呭ú鏍嵁閵忊�茬箚闁靛牆鎷戝妤冪磼閹插鐣垫慨濠傤煼瀹曟帒鈻庨幇顔哄仒婵犵數鍋涢ˇ鏉棵洪悢椋庢殾濞村吋娼欑粻娑㈡煛婢跺﹦浠㈤柛銈嗗灦缁绘繈鎮介棃娴讹綁鏌ら悷鏉库挃缂侇喖顭烽、娑㈡倷鐎电骞楅梻浣侯攰閹活亞寰婇崐鐕佹毐闂傚倷绀侀幖顐﹀箠韫囨稑绠栭柛灞惧嚬濞兼牗绻涘顔荤盎缂佺姰鍎查妵鍕即閻愭潙娅ゅ┑鐐茬墛婢瑰棛妲愰幘瀛樺闁告繂瀚ぐ娆撴⒑閹肩偛鍔橀柛鏂跨焸瀹曟粓顢楅崟顑芥嫼闂佸憡绋戦敃锕傚煡婢舵劖鐓ラ柡鍥敓鑺ョ箞閵嗕線寮崼婵嬪敹闂佸搫娲ㄩ崰搴㈢椤栫偞鈷戦梺顐ｇ☉瀹撳棙绻涙担鍐插椤╂彃霉閻撳海鎽犻柣鎾寸☉闇夐柨婵嗙墱濞兼劗锟借娲栭惉濂告晸閼恒儺鍟忛柛锝庡櫍瀹曟粓鎮㈤梹鎰畾闂佸壊鍋呭ú鏍嵁閵忊�茬箚闁靛牆鎷戝妤冪磼閹插鐣垫慨濠傤煼瀹曟帒鈻庨幇顔哄仒婵犵數鍋涢ˇ鏉棵洪悢椋庢殾濞村吋娼欑粻娑㈡煛婢跺﹦浠㈤柛銈嗗灦缁绘繈鎮介棃娴讹綁鏌ら悷鏉库挃缂侇喖顭烽、娑㈡倷鐎电骞楅梻浣侯攰閹活亞寰婇崐鐕佹毐闂傚倷绀侀幖顐﹀箠鎼淬劌绠烘繝濠傛噺椤洟鏌熼悜妯诲碍缂佸墎鍋涢埞鎴︽偐閹绘帗娈梺鍝勬閻楁挸顫忔ウ瑁や汗闁圭儤鎸婚弳鐘电磽娴ｇ瓔鍤欓柣妤佹崌閹即顢欑捄銊ф澑濠电偞鍨堕悷銉╂晸閽樺鐓奸柡灞诲�濋獮渚�骞掗幋婵嗩潥婵犵數鍋涢幊宀勫垂閽樺娼栭柧蹇氼潐瀹曞鏌曟繛鍨姕闁绘縿鍨藉娲偡閺夋寧顔�闂佺懓鍤栭幏锟�
		this.state = "standing";
		//闂傚倸鍊搁崐鎼佸磹瀹勬噴褰掑炊瑜夐弸鏍煛閸ャ儱鐏╃紒鎰殜楠炴牕菐椤掞拷閿熻姤鍨剁粋宥堛亹閹烘挾鍘繝鐢靛�崘顭戜患濡炪倕瀛╅惄顖炲蓟閿濆鍋勯柛婵勫劜閸Ｑ囨煟鎼淬垹鍤柛鎾跺枛閵嗕線寮崼婵堫槶婵炶揪缍�濞咃綁鏁嶅鍫熲拺闁告繂瀚弸锕傛煙妞嬪骸鍘撮柡宀嬬秮閹垽宕ｆ径瀣綃闁诲孩顔栭崳顕�宕戞繝鍥╁祦婵☆垰鍚嬬�氭岸鏌涘▎蹇ｆ▓婵☆偆鍠栧缁樼瑹閿熻棄顭囪閹囧幢濡炪垺绋戣灃闁告粈鐒﹂弲婊堟⒑閸撴彃浜濇繛鍙夛耿閸╂盯骞嬮敂钘変化闂佽鍘界敮鎺撲繆婵傚憡鐓涢悗锝庡亜閻忔挳鏌″畝瀣？闁跨喐鏋荤紞鍡樼閸洘鍋傞柛蹇曗拡濞堜粙鏌ｉ幇顖氱毢缂佺姴顭烽弻鐔碱敊閻ｅ瞼鐓夐梺鐟扮－閸嬨倖淇婇悜鑺ユ櫆缂佹稑顑勯幋鐑芥⒒閸屾艾锟介绮堟笟锟介獮鏍敃閿曪拷绾惧綊鏌涢锝嗙缁炬儳缍婇弻鈥愁吋鎼粹�插闂佺懓鍢查崲鏌ワ綖濠靛鍊锋い鎺炴嫹妞ゅ骏鎷�
		if(this.direction.equals("left"))
		{
			this.shownPic = staticVariable.HeroReady.get(0);
			this.maxX = shownPic.getSize().x;
			this.maxY = shownPic.getSize().y;
			isSet = false;
		}
		else if(this.direction.equals("right"))
		{
			this.shownPic = staticVariable.HeroReady.get(1);
			this.maxX = shownPic.getSize().x;
			this.maxY = shownPic.getSize().y;
			isSet = false;
		}
		isAttackFinish = false;
		isSkillFinish = false;
	}
	
}
