package com.yfl.base.base;

import android.widget.Toast;
import com.backpacker.yflLibrary.kotlin.NetWork;
import com.trello.rxlifecycle3.components.RxActivity;
import com.yfl.base.retrofit.net.ResultException;
import io.reactivex.*;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseHttps {

    protected <T> Callback<T> RxJavaCallBack(final CallBackInterface requestInterface) {
        Callback<T> callback = new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    T body = response.body();
                    requestInterface.success(body);
                } else {
                    Throwable throwable = new Throwable(response.message(), new ResultException());
                    requestInterface.error(throwable);
                }
                requestInterface.complate();
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                requestInterface.error(t);
                requestInterface.complate();
            }
        };
        return callback;
    }

    protected <T> Observer<T> RxJaveObserver(final CallBackInterface requestInterface) {
        Observer<T> observer = new Observer<T>() {

            @Override
            public void onError(Throwable e) {
                requestInterface.error(e);
            }

            @Override
            public void onComplete() {
                requestInterface.complate();
            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(T o) {
                requestInterface.success(o);
            }
        };

        return observer;
    }


    protected <T> void requestHttpRxjava(RxActivity mContext,
                                         final RequestInterface requestInterface,
                                         final CallBackInterface callBackInterface) {
        if (NetWork.Companion.isNetworkConnected(mContext)) {
            Observable.create(new ObservableOnSubscribe<T>() {
                @Override
                public void subscribe(final ObservableEmitter<T> emitter) throws Exception {
                    Call call = requestInterface.request();
                    call.enqueue(RxJavaCallBack(new CallBackInterface<T>() {
                        @Override
                        public void success(T o) {
                            emitter.onNext(o);
                        }

                        @Override
                        public void error(Throwable t) {
                            emitter.onError(t);

                        }

                        @Override
                        public void complate() {
                            emitter.onComplete();
                        }
                    }));
                }
            })
                    .compose(mContext.bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(RxJaveObserver(new CallBackInterface<T>() {
                        @Override
                        public void success(T o) {
                            callBackInterface.success(o);
                        }

                        @Override
                        public void error(Throwable t) {
                            callBackInterface.error(t);
                        }

                        @Override
                        public void complate() {
                            callBackInterface.complate();
                        }
                    }));

        } else {
            Toast.makeText(mContext, "网络异常，请检查网络", Toast.LENGTH_SHORT).show();
        }
    }


    public interface CallBackInterface<T> {
        public void success(T t);

        public void error(Throwable t);

        public void complate();
    }

    public interface RequestInterface<T> {
        public Call<T> request();
    }

}
