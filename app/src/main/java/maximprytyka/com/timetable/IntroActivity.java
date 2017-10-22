package maximprytyka.com.timetable;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.luseen.verticalintrolibrary.VerticalIntro;
import com.luseen.verticalintrolibrary.VerticalIntroItem;

public class IntroActivity extends VerticalIntro {


    @Override
    protected void init() {

        addIntroItem(new VerticalIntroItem.Builder()
                .backgroundColor(R.color.intro1)
                .image(R.drawable.edit)
                .title("Lorem Ipsum Lorem Ipsum")
                .text("Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.")
                .textSize(14)
                .titleSize(17)
                .build());

        addIntroItem(new VerticalIntroItem.Builder()
                .backgroundColor(R.color.intro2)
                .image(R.drawable.edit)
                .title("Lorem Ipsum Lorem Ipsum ")
                .text("Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.")
                .build());

        addIntroItem(new VerticalIntroItem.Builder()
                .backgroundColor(R.color.intro3)
                .image(R.drawable.edit)
                .title("Lorem Ipsum")
                .text("Lorem Ipsum is simply dummy text of the printing and typesetting industry.")
                .textColor(R.color.white)
                .titleColor(R.color.white)
                .build());

        addIntroItem(new VerticalIntroItem.Builder()
                .backgroundColor(R.color.intro4)
                .image(R.drawable.fon)
                .title("Lorem Ipsum")
                .text("Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.")
                .build());

        setSkipEnabled(true);
        setVibrateEnabled(true);
        setSkipColor(R.color.cardview_dark_background);
//        setNextText("OK");
//        setDoneText("FINISH HIM");
//        setSkipText("GO GO");
        setVibrateIntensity(20);
    }

    @Override
    protected Integer setLastItemBottomViewColor() {
        return R.color.intro1;
    }

    @Override
    protected void onSkipPressed(View view) {
        Log.e("onSkipPressed ", "onSkipPressed");
        Intent a=new Intent(IntroActivity.this,MainActivity.class);//Default Activity
        startActivity(a);
    }

    @Override
    protected void onFragmentChanged(int position) {
        Log.e("onFragmentChanged ", "" + position);
    }

    @Override
    protected void onDonePressed() {
       // Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
        Intent a=new Intent(IntroActivity.this,MainActivity.class);//Default Activity
        startActivity(a);
    }
}
