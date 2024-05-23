package thanh.edu.appdocsach.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class databasedocsach extends SQLiteOpenHelper {

    //CSDL
    //tên database
    private static String DATABASE_NAME = "doctruyen";

    //bảng tài khoản
    private static String TABLE_TAIKHOAN = "taikhoan";
    private static String ID_TAI_KHOAN = "idtaikhoan";
    private static String TEN_TAI_KHOAN = "tentaikhoan";
    private static String MAT_KHAU = "matkhau";
    private static String PHAN_QUYEN = "phanquyen";
    private static String EMAIL = "email";
    private static int VERSION = 1;

    //bảng sách
    private static String TABLE_TRUYEN = "truyen";
    private static String ID_TRUYEN = "idtruyen";
    private static String TEN_TRUYEN = "tieude";
    private static String NOI_DUNG = "noidung";
    private static String IMAGE = "anh";


    private Context context;

    //biến lưu câu lệnh tạo bảng tài khoản
    private String SQLQuery = "CREATE TABLE "+ TABLE_TAIKHOAN +" ( "+ID_TAI_KHOAN+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +TEN_TAI_KHOAN+" TEXT UNIQUE, "
            +MAT_KHAU+" TEXT, "
            +EMAIL+" TEXT, "
            + PHAN_QUYEN+" INTEGER) ";

    //biến lưu câu lệnh tạo bảng truyện
    private String SQLQuery1 = "CREATE TABLE "+ TABLE_TRUYEN +" ( "+ID_TRUYEN+" integer primary key AUTOINCREMENT, "
            +TEN_TRUYEN+" TEXT UNIQUE, "
            +NOI_DUNG+" TEXT, "
            +IMAGE+" TEXT, "+ID_TAI_KHOAN+" INTEGER , FOREIGN KEY ( "+ ID_TAI_KHOAN +" ) REFERENCES "+
            TABLE_TAIKHOAN+"("+ID_TAI_KHOAN+"))";

    //insert dữ liệu vào tài khoản
    private String SQLQuery2 = "INSERT INTO TaiKhoan VALUES (null,'admin','admin','admin@gmail.com',2)";
    private String SQLQuery3 = "INSERT INTO TaiKhoan VALUES (null,'thanh','thanh','thanh@gmail.com',1)";

    //insert truyện
    private String SQLQuery4 = "INSERT INTO truyen VALUES (null,'EM SỢ ĐIỀU GÌ NHẤT','Họ hỏi em sợ điều gì nhất.... em im lặng trong phút chốc rồi điềm nhiên trả lời:\n" +
            "\n" +
            "- Em sợ yêu nhất...\n" +
            "\n" +
            "***\n" +
            "\n" +
            "Có lẽ, cuộc đời này hạnh phút nhất chính là được yêu và được gặp được người mình yêu... vậy hà cớ gì mà em phải sợ? Em sợ những lời yêu thương chỉ tồn tại trong vài giây phút ngắn gủi, em sợ những lúc em yếu lòng lại ngả vào vòng tay của người lấy cắp đi niềm tin vào yêu thương của em, em sợ mình sẽ lại phải thấy sự phản bội đang ngày một ngày hai xoay em như một trò đùa, em sợ mình sẽ lại phí phạm thanh xuân mà mỗi đời người chỉ có một này để trao cho người không xứng đáng, và em sợ mình, sợ mình sẽ lại đặt niềm tin vào lầm người.\n" +
            "\n" +
            "em-so-dieu-gi-nhat\n" +
            "\n" +
            "Em có thể yêu nữa không? Có chứ....nhưng em sợ lắm, cái nỗi sợ này cứ vây quanh tâm trí em, nó đã lấy đi tất cả mọi thứ của em, lấy đi niềm tin của em và với em bây giờ chỉ còn một chút niềm tin nhỏ bé này em chỉ có thể ích kỉ giữ lại cho riêng bản thân mình, không giám để một ai lấy đi... lỡ họ lấy đi mất, em e rằng...em sẽ không trụ nổi.\n" +
            "\n" +
            "Trái tim mỏng manh này của em, không thể nào mạnh mẽ để vượt qua được hết năm lần bảy lượt sự phản bội được, vượt qua được một lần, em cứ nghĩ là do mình không may mắn, qua lần tiếp theo, em nghĩ mình không xứng đáng, rồi hết lần này qua lần khác, em còn có thể kiếm thêm một lí do nào cho hợp lí? Đến một ngày, một ngày bầu trời đầy giông bão đổ trên đầu em, dưới cơn mưa lạnh lẽo, em khóc giữa lòng người đang vội vã tìm chỗ trú mưa. Sao họ có thể vô tâm đến vậy? vô tâm để mình em gánh chịu cơn bão lớn này, sao lại có thể tàn nhẫn đến thế?\n" +
            "\n" +
            "Người ta yêu nhau và đến với nhau bởi duyên số, nhưng với em, duyên số là gì em chẳng biết, em chỉ biết rằng nếu đã yêu nhau, xin hãy yêu thật lòng, đừng phản bội nhau, đã yêu nhau là duy chỉ một. Em là cô gái được nhận nhiều sự phản bội, và em cũng sẽ kiên cường để không lỡ dại trót tin ai thêm nữa, em hứa đấy... nhưng hứa thế nào cũng không qua nổi sự sắp đặt của ông trời... họ hỏi em sợ gì nhất? em điềm nhiên trả lời:\n" +
            "\n" +
            "- Em sợ yêu nhất... nhưng cũng hi vọng vào tình yêu nhiều nhất...','https://www.truyenngan.com.vn/images/Phuongvtm/2020-09/em-so-dieu-gi-nhat.jpg',1)";

    private String SQLQuery5 = "INSERT INTO truyen VALUES (null, 'KHI TA GIÀ ĐI', 'Hai tuần lui tới bệnh viện, tận mắt chứng kiến những bệnh nhân cao tuổi ở Khoa tim mạch, tiếp xúc, nói chuyện với những bệnh nhân già, chợt nghĩ, đến một ngày ta già đi, ta sẽ tự nhủ mình rằng:\n" +
            "\n" +
            "Khi ta già đi, ta sẽ không lo nghĩ mấy cái chuyện bao đồng của con cháu 3 đời như cha mẹ ta thường lo lắng. Bởi con cháu đứa nào cũng có cuộc sống riêng của nó, ta cần sự tĩnh lặng để thư thái, nghỉ ngơi và cứ để mọi thứ thuận theo quy luật tự nhiên của cuộc sống.\n" +
            "\n" +
            "Khi ta già đi, ta sẽ không nhắc nhở con cháu về một điều gì đó quá cũ, rót vào tai chúng mãi một câu chuyện xa xưa, hay dặn dò chúng những điều hết sức lạc hậu. Cho dù nói nhiều hay ít thì con cháu nó vẫn cho rằng, đó chỉ là lý lẽ vớ vẩn của người già.\n" +
            "\n" +
            "Khi ta già đi, ta học cách buông tay, ta chẳng cần phải nghĩ ngợi những người trẻ mỗi ngày nó lướt qua đời ta một cách nhẹ nhàng như thế nào, ta chỉ mong mình đủ sức để tự nấu một món ăn thật nhừ, đủ sức để tự tắm rửa hàng ngày, ta cũng không câu nệ mọi ngóc ngách trong nhà đều phải tinh tươm sạch sẽ, mà chỉ chú ý đến sức khỏe của bản thân mình, nhằm giảm bớt áp lực cho con cháu khi phải chăm sóc ta.\n" +
            "\n" +
            "Khi ta già đi, ta cũng mong trời thương, nằm xuống, nhắm mắt một phát là đi ngay. Bởi ta đã nhìn thấy nhiều cảnh đau lòng xảy ra khi con cái chăm sóc cha mẹ già. Có nhiều chuyện bấy lâu nay ta cứ nghĩ nó chỉ có trong văn học thời hiện thực phê phán, nhưng không, nó vẫn được diễn ra trong cuộc sống thường nhật. Mà nghĩ đi rồi cũng nghĩ lại, thực ra thì con cái ai cũng tốt và có hiếu với cha mẹ cả thôi, nhưng khi bố mẹ trở thành gánh nặng hàng ngày, nhiều ngày thì tình cảm sẽ mai một dần đến mức không còn thấy thương yêu nữa, chỉ còn thấy chịu đựng, thấy khổ sở, nhất là con rể hoặc dâu và cháu, những người không trực hệ, không được ông bà trực tiếp đẻ ra.\n" +
            "\n" +
            "Và khi ta già đi, ta muốn chẳng nợ nần gì nhau trong cuộc đời này, chỉ cần được an yên, bởi ta nhĩ bất cứ ai, cho dù một thời trẻ hoài bão, ngang dọc, vội vã, và rồi cũng đến lúc già, chỉ cần hai chữ an yên mà thôi.','https://www.truyenngan.com.vn/images/Phuongvtm/2020-08/khi-ta-gia-di.jpg',1)";

    private String SQLQuery6 = "INSERT INTO truyen VALUES (null, 'NỖI BUỒN BỎ NGỎ', 'Tôi vẫn thường nói đùa rằng: \"1 người chuyên làm radio cho người khác thì sẽ không bao giờ có số radio nào cho riêng mình\". Nhưng hôm nay, trong cái nắng đầu thu thắm đượm màu phân li – cái tháng mà 1 năm trước tôi rời xa gia đình với bao rạo rực, bồn chồn của đứa con gái cầm trên tay giấy trúng tuyển đại học. Để đến bây giờ lại muốn viết và nói gì đó cho thỏa 1 nổi lòng ưu tư.\n" +
            "\n" +
            "***\n" +
            "\n" +
            "Có những ngày nỗi buồn như xé nát tâm can. Trời vẫn trong, gió vẫn hát, tán cây già vẫn lay mình đung đưa trong khúc nhạc mây trời, thành phố vẫn thế, dòng người vẫn vậy, vẫn bình yên sống cho hết một ngày dài, chỉ riêng lòng mình là bão tố đầy dâng. Có đôi ba lần ngẩn mặt lên trời tự hỏi: \"Sao bầu trời trong xanh đến thế?\" Lòng trời thì xanh thẳm mà lòng người thì lại dậy sóng\". Đôi bàn chân vẫn hiên ngang bước, miệng vẫn nhỏen cười mà khóe mi ươn ướt.\n" +
            "\n" +
            "Thành phố này to lớn là thế ấy vậy mà chẳng có nơi nào để tôi chọn là góc riêng của bản thân mình, không có nơi nào để tôi nấu thân mỗi lúc nỗi buồn chế ngự. Biển á? Không, biển mênh mông nhưng lòng tôi lại mênh mông hơn biển. Chỉ sợ bản thân đối mặt với những con sóng cuộn tròn vỗ bờ cát trắng xóa thì lòng lại thênh thang hơn lúc ban đầu. Có những ngày tỉnh giấc, chợt thấy lòng mình vơi đi chút thiết tha. Đã hơn 1 lần cảm thấy chênh vênh bên những nổi buồn không rõ nguyên do cứ lặp đi lặp lại. Chúng góp nhặt mỗi ngày một chút ưu tư, một chút phiền muộn để đến lúc xế chiều, khi hoàng hôn dần buông với đám mây tím mải mê đùa giỡn trên nền trời ảm đạm lại thấy lòng có chút chênh chao với một nỗi buồn mơ hồ xa xăm. Giá mà nổi buồn như những tờ giấy, thì tôi sẽ vò chúng lại và đốt thành tro.\n" +
            "\n" +
            "Lúc mà màn đêm ôm trọn thành phố vào lòng. Đèn cao áp từ từ chiếu sáng con đường quốc lộ, mà bản thân cũng thèm có một loại đèn chiếu sáng vào góc khuất tâm can.Tôi thích dạo phố khi màn đêm buông xuống, không hiểu sao nhưng tôi thấy lòng mình bình yên đến lạ. Bước chân vô định vẫn âm thầm hòa vào giữa hàng nghìn bước chân khác. Đôi mắt lại lãng đãng xa xăm.\n" +
            "\n" +
            "Có những ngày đội nắng đến mặt trời trên con đường đắm chìm trong mật vàng, bỏ lại sau lưng những bộn bề của cuộc sống thường ngày, tôi bắt chuyến xe buýt về với quê Quảng, không hiểu sao tôi thấy lòng bình yên hẳn khi về với quê làng. Xe buýt vẫn lăn bánh, tôi ngả đầu vào ghế còn bàn tay thì vẽ về 1 thứ hình ảnh không đầu không cuối in hằn lên ô cửa bị bụi bẩn vấy mờ.', 'https://www.truyenngan.com.vn/images/Phuongvtm/2020-09/noi-buon-bo-ngo.jpg',1)";

    private String SQLQuery7 = "INSERT INTO truyen VALUES (null, 'ỐNG PHỐC', 'Cũng lâu lắm rồi mới lại về thăm quê, con đường làng quen thuộc đã phần nào bê tông hóa, màu thành phố đã bao trùm cả làng quê nhỏ bé của tôi, chỉ có vài nơi dường như vẫn thu mình lại, sống cuộc sống trầm mặc, thoát khỏi hiện thực. Quê tôi ở cheo leo vách núi, nơi đó ngày xưa ông bà tôi lặn lội từ Bắc vào khai phá làm vùng kinh tế mới. Thời gian dần trôi qua, cũng gần cả chục năm trời tôi mới quay lại nơi đây, nhiều thứ ấn tượng khó phai từ sâu trong tâm trí bỗng cựa mình thức dậy.\n" +
            "\n" +
            "Tôi đi dạo loanh quanh trong xóm, vùng đất nghèo này đã bắt đầu khởi sắc khi nhà thờ Bác được xây nên. Người qua lại bắt đầu đông hơn, những con đường nơi phố núi bắt đầu trở mình căng tràn sức sống, khoác lên mình chiếc áo bê tông rất mới. " +
            "\n"+
            "Đường sá dễ đi hơn, người bắt đầu qua lại tấp nập. Như bỗng sực nhớ ra điều gì, tôi quay bước đi về lối cũ, nơi đây hãy còn thưa vắng lắm, đó là con đường vào rẫy, khi qua hết mảnh đất rẫy rộng sẽ tới một cái hồ nhỏ, nơi bác Tư Thuyền vẫn hay chèo ghe giăng lưới. Ở đó có mảnh đất bên sông rất rộng nơi chúng tôi bảy ra đủ trò mà chơi. Cạnh đó vẫn còn căn chòi nhỏ nhưng đã không còn người ở, gió thổi quật rách, đã một thời là chỗ chui ra chui vào của bác Tư. Khi bác mất con cái chuyển đi, \"ngôi nhà xưa\" bị quên lãng... Lũ trẻ con chúng tôi buổi đi học, buổi theo ba mẹ đi làm rẫy, cuộc sống mưu sinh gắn chặt cùng với tuổi thơ. " +
            "\n" +
            "Nơi đây khi ấy vẫn còn thưa người lắm, những ngôi nhà cách xa nhau bằng những sào rẫy, heo hút lắm mới có vài căn. Hàng xóm người này í ới gọi qua người kia nhờ gió đưa tiếng gọi mới nghe được. Chúng tôi tự nghĩ ra cách để làm vui cho mình. Một trong những trò mà chúng tôi thích chơi đó là làm súng ống phốc.', 'https://www.truyenngan.com.vn/images/linhbp/2018-truyen-ngan/ong-phoc.jpg',1)";

    private String SQLQuery8 = "INSERT INTO truyen VALUES (null, 'THOẢNG THEO GIÓ TRỜI', 'Em là một đứa trẻ không có tuổi thơ. Em từng bị trầm cảm suốt 4 năm trời chỉ vì sự dè bỉu của xã hội. Trong khi lũ trẻ cùng tuổi hòa mình vào những trò chơi tập thể thì em lại bị cô lập, hắt hủi. Đôi khi ước mơ nhỏ bé của em cũng chỉ là được ngẩng mặt hiên ngang với đời, vậy mà cũng bị vùi dập bởi một xã hội chẳng mấy thiết tha...\n" +
            "\n" +
            "Tháng 8 - tháng mà em rời xa sự an yên, bình lặng của gia đình để bắt đầu một cuộc sống mới. Em không đi theo con đường đã vạch sẵn của các chị. Em không thích những nơi quá ồn ào, quá tấp nập mà lòng lại lạc lõng chơi vơi như miền Nam. Em chọn Đà Nẵng!\n" +
            "\n" +
            "Thật ra ở bất kỳ thành phố nào ta cũng thấy lòng lạc lõng, thấy trái tim chệch nhịp giữa hàng ngàn trái tim hối hả, xô bồ khác thôi, nhưng Đà Nẵng lại mang trong mình hơi thở an yên, bình lặng khác xa cái ồn ào vốn có của một thành phố tráng lệ.\n" +
            "\n" +
            "27/08 - Dấu mốc quan trọng. Như mọi năm, trời lại đổ mưa, những hạt mưa lạnh lùng giăng kín lối. Những tưởng em sẽ đón sinh nhật trong cái lặng lẽ của thành phố, ấy vậy mà những đứa bạn cùng kí túc xá không để em như thế. Có thể ở một thành phố xa lạ những con người đồng trang lứa lại dễ dàng thấu hiểu và san sẻ với nhau nhiều hơn trong cùng một hoàn cảnh - xa nhà.\n" +
            "\n" +
            "Thời gian trôi nhanh như cái quay lưng chớp mắt của kẻ hững hờ. Ngày em 18, cuộc đời lại tiếp tục chuyển qua một gam màu mới. Phải chấp nhận rằng mọi thứ đã chẳng còn ưu ái và dễ dàng với ta như lúc 17. Có những khó khăn khiến em mỏi mệt. Em bắt đầu có những giọt nước mắt đầu tiên. Em khóc nhiều hơn mỗi khi thành phố lên đèn. Em thấy tủi thân, thấy lòng mình ngổn ngang, hỗn độn. Em nhận ra Người ta sẽ không trả lương nếu họ không vắt kiệt sức lao động của bạn. Em nhận ra Ngoài gia đình, những thứ khác tồn tại hay không, không quan trọng. Em cũng nhận ra Nếu cuộc đời có đạp mình sấp mặt thì cũng phải biết nhe hàm răng ra cười. \n" +
            "\n" +
            "Em quen với sự im lặng, quen với một kiếp sống quẩn quanh cùng những nỗi buồn cứ mãi ôm trọn vào lòng. Em không thích cứ có tí chuyện là lại than thở, nó chẳng giúp ích gì cho bản thân cả, ngược lại nó còn làm bào mòn tâm hồn. Có những ngày mệt mỏi, lòng ngổn ngang xúc cảm bản thân lại chỉ muốn khóc. Khóc sẽ chẳng giải quyết được vấn đề gì nhưng ít nhất nó cũng làm ta nhẹ nhõm hơn. Nếu một ngày bạn thấy lòng bộn bề và tâm chẳng mấy an yên thì cứ khóc đi. Tự khóc rồi tự lau. Nhưng... khóc thôi, đừng lụy!', 'https://www.truyenngan.com.vn/images/Phuongvtm/2020-09/thoang-theo-gio-troi.jpg',1)";

    //tạo bảng tại phương thức này
    public databasedocsach(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //thực hiện truy vấn không trả kết quả
        db.execSQL(SQLQuery);
        db.execSQL(SQLQuery1);
        db.execSQL(SQLQuery2);
        db.execSQL(SQLQuery3);
        db.execSQL(SQLQuery4);
        db.execSQL(SQLQuery5);
        db.execSQL(SQLQuery6);
        db.execSQL(SQLQuery7);
        db.execSQL(SQLQuery8);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
