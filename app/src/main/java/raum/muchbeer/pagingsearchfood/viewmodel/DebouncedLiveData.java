package raum.muchbeer.pagingsearchfood.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import android.os.Handler;

public class DebouncedLiveData<T> extends MediatorLiveData<T> {

    private LiveData<T> mSource;
    private int mDuration;
    private Runnable debounceRunnable = new Runnable() {
        @Override
        public void run() {
            DebouncedLiveData.this.postValue(mSource.getValue());
        }
    };
    private Handler handler = new Handler();

    public DebouncedLiveData(LiveData<T> source, int duration) {
        this.mSource = source;
        this.mDuration = duration;

        this.addSource(mSource, new Observer<T>() {
            @Override
            public void onChanged(T t) {
                handler.removeCallbacks(debounceRunnable);
                handler.postDelayed(debounceRunnable, mDuration);
            }
        });
    }
}
