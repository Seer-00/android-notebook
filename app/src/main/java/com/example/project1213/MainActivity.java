package com.example.project1213;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private boolean initDatabase = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button skip = (Button)findViewById(R.id.to_skip);
        Button TB = (Button)findViewById(R.id.TB);

        Connector.getDatabase();

        List<Account> accountList = LitePal.select("userName").find(Account.class);

        if(accountList == null)
        {
            Toast.makeText(MainActivity.this, "Database Error",
                    Toast.LENGTH_SHORT).show();
        }

        if(accountList != null && accountList.size() == 0) {
            createUser_1();
            createUser_2();
            createUser_3();
            createUser_4();
            createUser_5();
            createUser_6();
            createUser_7();
            createUser_8();
            createUser_9();
            createUser_10();
            createUser_11();
            createUser_12();
            initDatabase = true;
            skip.setVisibility(View.VISIBLE);
        }
        else{
            skip.setVisibility(View.VISIBLE);
        }

        // Button of skip
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SkipActivity.class);
                startActivity(intent);
                finish();
            }
        });



        // Button to Taobao
        TB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tbPath="https://detail.tmall.com/item.htm?spm=a220m.1000858.1000725.6.1f7a30aeWnGNjY&id=548774633339&skuId=4611686567202021243&standard=1&user_id=2041592426&cat_id=2&is_b=1&rn=afe1065807d473a20804abaa87bc88df";
                if(checkPackage(tbPath))totianmao(tbPath);
                else{
                    Intent intent = new Intent(MainActivity.this,WebView.class);
                    intent.putExtra("website_address", tbPath);
                    intent.putExtra("website_name","淘宝");
                    startActivity(intent);
                }
            }
        });

        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(initDatabase){
                    Intent intent = new Intent(MainActivity.this, SkipActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 12000);

    }

    public void createUser_1(){
        String usr_n = "小红";
        String usr_p = "Vm0wd2QyUXlWa2";
        String rec_title = "丹霞山之旅";
        String rec_text = "今天终于来到了丹霞山，广东第一峰果然名不虚传呀！虽然不算很高，但也能感受到云雾缭绕的感觉，真是太棒了！";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis() - (int)(Math.random()*2678400000L));
        String date = formatter.format(curDate);

        Bitmap mBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.headshot_1)).getBitmap();
        byte[] im = img(mBitmap);

        if (im.length == 0) {
            Log.d(TAG, "onCreate: Image is NULL");
            return;
        }

        Account account = new Account();
        account.setUserName(usr_n);
        account.setPassword(usr_p);
        account.setUserImage(im);
        account.save();

        Record record = new Record();
        record.setRecordTitle(rec_title);
        record.setRecordText(rec_text);
        record.setRecordDate(date);
        record.save();

        account.getRecordList().add(record);
        account.save();
    }
    public void createUser_2(){
        String usr_n = "寒号鸟";
        String usr_p = "UlRCV01uUnZ";
        String rec_title = "顺德清晖园";
        String rec_text = "清晖园位于广东省佛山市顺德区大良镇华盖里，为我国南方古典园林艺术的杰作，素有岭南四大名园之一的盛誉，" +
                "已列为省级文物保护单位。其布局既能吸取苏州园林艺术精华，又能因地制宜，环境以清幽自然、秀丽典雅见称。" +
                "著名作家朱千华先生在园林文化随笔集《雨打芭蕉落闲庭·岭南画舫录》中，对顺德“清晖园”有详尽描述。";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis() - (int)(Math.random()*2678400000L));
        String date = formatter.format(curDate);

        Bitmap mBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.headshot_2)).getBitmap();
        byte[] im = img(mBitmap);

        if (im.length == 0) {
            Log.d(TAG, "onCreate: Image is NULL");
            return;
        }

        Account account = new Account();
        account.setUserName(usr_n);
        account.setPassword(usr_p);
        account.setUserImage(im);
        account.save();

        Record record = new Record();
        record.setRecordTitle(rec_title);
        record.setRecordText(rec_text);
        record.setRecordDate(date);
        record.save();

        account.getRecordList().add(record);
        account.save();
    }
    public void createUser_3(){
        String usr_n = "Zzh0101";
        String usr_p = "Fcxb1VWWnJXbGRaVm";
        String rec_title = "黄山";
        String rec_text = "最好的风景就是导游不带的，XX大峡谷，那风景才是真正的自然，大气，无比陡峭的悬崖，" +
                "一望无际的山峦。真是太美了——很多人都没去的——就是太危了。";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis() - (int)(Math.random()*2678400000L));
        String date = formatter.format(curDate);

        Bitmap mBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.headshot_3)).getBitmap();
        byte[] im = img(mBitmap);

        if (im.length == 0) {
            Log.d(TAG, "onCreate: Image is NULL");
            return;
        }

        Account account = new Account();
        account.setUserName(usr_n);
        account.setPassword(usr_p);
        account.setUserImage(im);
        account.save();

        Record record = new Record();
        record.setRecordTitle(rec_title);
        record.setRecordText(rec_text);
        record.setRecordDate(date);
        record.save();

        account.getRecordList().add(record);
        account.save();
    }
    public void createUser_4(){
        String usr_n = "Zzy";
        String usr_p = "RyV210VWJGcDFV";
        String rec_title = "华山";
        String rec_text = "临时决定的一次旅行，由马小胖导游带的队，选的西上北下线路，6:30在西华门消防队集合出发，" +
                "这条线路比较科学，稍快点可以5个峰都走到，风景不错，大部分都是下山路，相对北上的线路省力很多，" +
                "吃的喝的也不要带太多，毕竟要爬山的，一个人2瓶水，一份面包就好了，省省肚子回城后吃好吃的小吃，" +
                "马导服务超好，讲解到位，安全提示贴心，还推荐了吃货必吃美食，当天就去了王魁腊汁肉夹馍，真是好吃，" +
                "回民街的是不能比的，6619的司机师傅开车很稳当，一次满意行程";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis() - (int)(Math.random()*2678400000L));
        String date = formatter.format(curDate);

        Bitmap mBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.headshot_4)).getBitmap();
        byte[] im = img(mBitmap);

        if (im.length == 0) {
            Log.d(TAG, "onCreate: Image is NULL");
            return;
        }

        Account account = new Account();
        account.setUserName(usr_n);
        account.setPassword(usr_p);
        account.setUserImage(im);
        account.save();

        Record record = new Record();
        record.setRecordTitle(rec_title);
        record.setRecordText(rec_text);
        record.setRecordDate(date);
        record.save();

        account.getRecordList().add(record);
        account.save();
    }
    public void createUser_5(){
        String usr_n = "稀饭";
        String usr_p = "DNXa1JTVjFac2JETlh";
        String rec_title = "杭州西湖";
        String rec_text = "西湖位于杭州市中心不远，夕照美景闻名于世，今天终于有机会目睹，西湖有山有水有江南庭园景致，" +
                "其中苏公堤与白堤横跨于湖中，散步于堤上，听着湖水因游湖船只往来穿梭，引起的波浪拍打岸边，别有一番滋味。" +
                "唯游客如织少了一分宁静之美⋯⋯";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis() - (int)(Math.random()*2678400000L));
        String date = formatter.format(curDate);

        Bitmap mBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.headshot_5)).getBitmap();
        byte[] im = img(mBitmap);

        if (im.length == 0) {
            Log.d(TAG, "onCreate: Image is NULL");
            return;
        }

        Account account = new Account();
        account.setUserName(usr_n);
        account.setPassword(usr_p);
        account.setUserImage(im);
        account.save();

        Record record = new Record();
        record.setRecordTitle(rec_title);
        record.setRecordText(rec_text);
        record.setRecordDate(date);
        record.save();

        account.getRecordList().add(record);
        account.save();
    }
    public void createUser_6(){
        String usr_n = "HXF";
        String usr_p = "pHUjFOdVRtcFRSVXBZV1ZS";
        String rec_title = "上海迪士尼";
        String rec_text = "提前买了VIP票避免了排队的烦恼。\n" +
                "但是跟东京迪士尼的热情服务相比，上海的迪士尼冷淡了不少，对于小朋友没那么热情之外，" +
                "客人也有很多的熊孩子让人非常苦恼。不遵守吸烟区行为的人和带着孩子直径走进吸烟区的人都很令人反感，" +
                "但是并没有见到工作人员的劝阻。\n" +
                "如果一定想去玩的话，请工作日去，很多游戏项目只需要排队10分钟。";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis() - (int)(Math.random()*2678400000L));
        String date = formatter.format(curDate);

        Bitmap mBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.headshot_6)).getBitmap();
        byte[] im = img(mBitmap);

        if (im.length == 0) {
            Log.d(TAG, "onCreate: Image is NULL");
            return;
        }

        Account account = new Account();
        account.setUserName(usr_n);
        account.setPassword(usr_p);
        account.setUserImage(im);
        account.save();

        Record record = new Record();
        record.setRecordTitle(rec_title);
        record.setRecordText(rec_text);
        record.setRecordDate(date);
        record.save();

        account.getRecordList().add(record);
        account.save();
    }
    public void createUser_7(){
        String usr_n = "Steve";
        String usr_p = "LILo9k1l93kg09";
        String rec_title = "香港海洋公园";
        String rec_text = "想像中的海洋公园已经经营十多年 有种陈旧的感觉，但意想不到的是乐园维持得十分好，" +
                "更加有不少新增的设施及玩乐游戏，年青人和家庭都很适合游览的主题乐园\n" +
                "海洋公园有适合青年人玩的刺激机动游戏，也有儿童天地和很多动物馆适合一家大小的游客。" +
                "这里地方很大，一天也未必能够走得远全园。";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis() - (int)(Math.random()*2678400000L));
        String date = formatter.format(curDate);

        Bitmap mBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.headshot_7)).getBitmap();
        byte[] im = img(mBitmap);

        if (im.length == 0) {
            Log.d(TAG, "onCreate: Image is NULL");
            return;
        }

        Account account = new Account();
        account.setUserName(usr_n);
        account.setPassword(usr_p);
        account.setUserImage(im);
        account.save();

        Record record = new Record();
        record.setRecordTitle(rec_title);
        record.setRecordText(rec_text);
        record.setRecordDate(date);
        record.save();

        account.getRecordList().add(record);
        account.save();
    }
    public void createUser_8(){
        String usr_n = "Swift";
        String usr_p = "rcFVUa1UlM0Q=";
        String rec_title = "哈尔滨 冰雪大世界";
        String rec_text = "那天跨年，我和我的小伙伴们去了哈尔滨冰雪大世界，真的很冷但是也很开心。" +
                "冰雪大世界真的很美值得大家来，适合跨年哦。";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis() - (int)(Math.random()*2678400000L));
        String date = formatter.format(curDate);

        Bitmap mBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.headshot_8)).getBitmap();
        byte[] im = img(mBitmap);

        if (im.length == 0) {
            Log.d(TAG, "onCreate: Image is NULL");
            return;
        }

        Account account = new Account();
        account.setUserName(usr_n);
        account.setPassword(usr_p);
        account.setUserImage(im);
        account.save();

        Record record = new Record();
        record.setRecordTitle(rec_title);
        record.setRecordText(rec_text);
        record.setRecordDate(date);
        record.save();

        account.getRecordList().add(record);
        account.save();
    }
    public void createUser_9(){
        String usr_n = "Jack";
        String usr_p = "lrWmFlVmRyV210";
        String rec_title = "不同的时节，不同的少林";
        String rec_text = "秋冬季的少林景区，一片金黄，感觉\"满城尽带黄金甲\"，加上少林功夫的衬托，给你不一样的感觉，" +
                "再加上僧侣的劳作，更是一种美！有机会来感受一下吧！对了，给大家推荐一下他们的一个不错的讲解，" +
                "洛阳天天旅行社的小张。讲解风趣幽默，文采过人！";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis() - (int)(Math.random()*2678400000L));
        String date = formatter.format(curDate);

        Bitmap mBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.headshot_9)).getBitmap();
        byte[] im = img(mBitmap);

        if (im.length == 0) {
            Log.d(TAG, "onCreate: Image is NULL");
            return;
        }

        Account account = new Account();
        account.setUserName(usr_n);
        account.setPassword(usr_p);
        account.setUserImage(im);
        account.save();

        Record record = new Record();
        record.setRecordTitle(rec_title);
        record.setRecordText(rec_text);
        record.setRecordDate(date);
        record.save();

        account.getRecordList().add(record);
        account.save();
    }
    public void createUser_10(){
        String usr_n = "晓、拾侊";
        String usr_p = "tkWGJGcE9ZV3RGZUZ";
        String rec_title = "有点过于商业化的故宫";
        String rec_text = "好几年没来故宫了，这次带朋友一起来，总的来说有点失望。\n" +
                "故宫还是最美的最恢弘的最大气的故宫。失望的是它的管理，似乎开始配不上故宫在我国旅游或者说文化产业中的地位了。\n" +
                "门口一人花20元租的讲解器已经过时了，走到很多地方都不能及时定位开启讲解。\n" +
                "故宫内的文创店过多，产品重复。最开始我是非常喜欢故宫开启的文创风潮，但今天去看，实在有些惊到。" +
                "问题主要在于：1 店铺过多过于分散，集中在一个区域如出口处会使故宫的游览更加整体化；" +
                "2 店与店之间卖的产品有交叉有重复，零散； 3 有点过了。故宫，是中国的故宫没错，但故宫的历史不过明清时期的几百年。" +
                "卖《千里江山图》和《清明上河图》的意思是？？？难道没有真正属于故宫自己的产品可开发吗？？\n" +
                "失望，希望故宫的管理层早点看见这些问题，尽早止损，不然故宫作为中国招待世界的第一张面孔，实在有些贻笑大方。";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis() - (int)(Math.random()*2678400000L));
        String date = formatter.format(curDate);

        Bitmap mBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.headshot_10)).getBitmap();
        byte[] im = img(mBitmap);

        if (im.length == 0) {
            Log.d(TAG, "onCreate: Image is NULL");
            return;
        }

        Account account = new Account();
        account.setUserName(usr_n);
        account.setPassword(usr_p);
        account.setUserImage(im);
        account.save();

        Record record = new Record();
        record.setRecordTitle(rec_title);
        record.setRecordText(rec_text);
        record.setRecordDate(date);
        record.save();

        account.getRecordList().add(record);
        account.save();
    }
    public void createUser_11(){
        String usr_n = "傻小子";
        String usr_p = "RWTVdoM1ZqSktSMk5JU2x";
        String rec_title = "莫高窟——佛教造像艺术的巅峰";
        String rec_text = "清末 一个道士的偶然发现 一个英国人的设计盗宝 一个法国人的多方宣扬 " +
                "三危山莫高窟千佛洞的珍藏才得重现世人眼前 且研究者众多 形成所谓的敦煌学\n" +
                "现在的莫高窟 有一批出色的专业解说员 听他们细数自魏晋以至唐宋五代 " +
                "旁及西夏回鹘 不同时期各自的风格 不虚此行";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis() - (int)(Math.random()*2678400000L));
        String date = formatter.format(curDate);

        Bitmap mBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.headshot_11)).getBitmap();
        byte[] im = img(mBitmap);

        if (im.length == 0) {
            Log.d(TAG, "onCreate: Image is NULL");
            return;
        }

        Account account = new Account();
        account.setUserName(usr_n);
        account.setPassword(usr_p);
        account.setUserImage(im);
        account.save();

        Record record = new Record();
        record.setRecordTitle(rec_title);
        record.setRecordText(rec_text);
        record.setRecordDate(date);
        record.save();

        account.getRecordList().add(record);
        account.save();
    }
    public void createUser_12(){
        String usr_n = "小红薯";
        String usr_p = "";
        String rec_title = "来西藏，去布达拉宫是第一件事";
        String rec_text = "布达拉宫（藏语拼音：bo da la，威利：po ta la），" +
                "坐落于中国西藏自治区的首府拉萨市区西北玛布日山上，是世界上海拔最高，" +
                "集宫殿、城堡和寺院于一体的宏伟建筑，也是西藏最庞大、最完整的古代宫堡建筑群。 " +
                "布达拉宫依山垒砌，群楼重叠，殿宇嵯峨，气势雄伟，是藏式古建筑的杰出代表，" +
                "中华民族古建筑的精华之作，是第五套人民币50元纸币背面的风景图案 。" +
                "主体建筑分为白宫和红宫两部分。宫殿高200余米，外观13层，内为9层。";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis() - (int)(Math.random()*2678400000L));
        String date = formatter.format(curDate);

        Bitmap mBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.headshot_12)).getBitmap();
        byte[] im = img(mBitmap);

        if (im.length == 0) {
            Log.d(TAG, "onCreate: Image is NULL");
            return;
        }

        Account account = new Account();
        account.setUserName(usr_n);
        account.setPassword(usr_p);
        account.setUserImage(im);
        account.save();

        Record record = new Record();
        record.setRecordTitle(rec_title);
        record.setRecordText(rec_text);
        record.setRecordDate(date);
        record.save();

        account.getRecordList().add(record);
        account.save();
    }

    private byte[]img(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }


    public boolean checkPackage(String packageName)
    {

        if (packageName == null || "".equals(packageName)) return false;
        try
        {
            this.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            return false;
        }

    }

    void totianmao(String tbPath){
        Intent intent = new Intent();
        intent.setAction("Android.intent.action.VIEW");
        Uri uri = Uri.parse(tbPath); // 商品地址
        intent.setData(uri);
        intent.setClassName("com.taobao.taobao", "com.taobao.tao.detail.activity.DetailActivity");
        startActivity(intent);
    }

}
