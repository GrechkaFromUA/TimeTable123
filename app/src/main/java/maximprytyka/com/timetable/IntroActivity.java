package maximprytyka.com.timetable;


import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.luseen.verticalintrolibrary.VerticalIntro;
import com.luseen.verticalintrolibrary.VerticalIntroItem;

public class IntroActivity extends VerticalIntro {


    @Override
    protected void init() {

        addIntroItem(new VerticalIntroItem.Builder()
                .backgroundColor(R.color.intro1)
                .image(R.drawable.intro_1)
                .title(getString(R.string.intro1_screen1))
                .text(getString(R.string.intro1_screen2) + "\n\n\n")
                .textSize(15)
                .titleSize(24)
                .build());

        addIntroItem(new VerticalIntroItem.Builder()
                .backgroundColor(R.color.intro2)
                .image(R.drawable.intro_3)
                .title(getString(R.string.intro3_screen1))
                .text(getString(R.string.intro3_screen2) + "\n\n\n")
                .textSize(15)
                .titleSize(24)
                .build());

        addIntroItem(new VerticalIntroItem.Builder()
                .backgroundColor(R.color.intro3)
                .image(R.drawable.intro_2)
                .title(getString(R.string.intro2_screen1))
                .text(getString(R.string.intro2_screen2 ) + "\n\n\n")
                .textSize(15)
                .titleSize(24)
                .build());

        setSkipEnabled(true);
        setVibrateEnabled(true);
        setSkipColor(R.color.white);
        setNextText(getString(R.string.next));
        setDoneText(getString(R.string.done));
        setSkipText(getString(R.string.skip));
        setVibrateIntensity(20);
    }

    @Override
    protected Integer setLastItemBottomViewColor() {
        return R.color.intro4;
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
