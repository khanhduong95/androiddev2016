package feedr.usth.tanu.feedr;

import android.media.Image;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private Image imgHeader1, imgHeader2;
    private TextView title1, title2;
    private TextView description1, description2;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        title1 = (TextView) getView().findViewById(R.id.title1);
        title1.setText("Canon ra mắt DSLR phổ thông EOS 1300D/T6 cấu hình thấp, giá chỉ £290");

        title2 = (TextView) getView().findViewById(R.id.title2);
        title2.setText("Nom - dịch vụ livestream dành cho các bà nội trợ, đầu bếp hay người yêu ẩm thực");

        description1 = (TextView) getView().findViewById(R.id.description1);
        description1.setText("Nếu bạn là một người mới bắt đầu tiếp xúc với DSLR và cần tìm một chiếc máy ảnh giá rẻ thì T6 sẽ là một sự lựa chọn tốt. Máy có cấu hình không quá mạnh mẽ với cảm biến 18MP, chip xử lý ảnh Digic 4+ nhưng bù lại có một mức giá khá phải chăng, chỉ £290, xấp xỉ 9,5 triệu đồng...");

        description2 = (TextView) getView().findViewById(R.id.description2);
        description2.setText("Sau game và nội dung giải trí thì hôm nay, chúng ta còn có thể xem nấu ăn trực tuyến qua dịch vụ mới có tên Nom do nhà đồng sáng lập YouTube - Steve Chen phát hành. Đây là một nền tảng phát nội dung trực tuyến (livestream) hướng đến những người sành ăn, các bà nội trợ, đầu bếp chuyên nghiệp và bất cứ ai yêu thích nghệ thuật nấu nướng");

    }
}
