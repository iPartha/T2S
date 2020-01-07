// MainActivity.java
package com.ipartha.t2s;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelProvider.Factory;
import com.ipartha.t2s.data.Result;
import com.ipartha.t2s.databinding.ActivityMainBinding;
import com.ipartha.t2s.databinding.ErrorLayoutBinding;
import com.ipartha.t2s.mvvm.ConsumerMenuRepository;
import com.ipartha.t2s.mvvm.ConsumerMenuViewModel;
import com.ipartha.t2s.mvvm.ConsumerMenuViewModelFactory;
import com.ipartha.t2s.ui.ViewExtensions;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public final class MainActivity extends AppCompatActivity {
    @NotNull
    public ConsumerMenuViewModel consumerMenuViewModel;
    @NotNull
    public View mErrorLayout;
    private OnClickListener retryClick = (OnClickListener)(new OnClickListener() {
        public final void onClick(View it) {
            ViewExtensions.hide(MainActivity.this.getMErrorLayout());
            MainActivity.this.getConsumerMenuViewModel().refreshConsumerMenu();
        }
    });
    private HashMap _$_findViewCache;

    @NotNull
    public final ConsumerMenuViewModel getConsumerMenuViewModel() {
        ConsumerMenuViewModel var10000 = this.consumerMenuViewModel;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("consumerMenuViewModel");
        }

        return var10000;
    }

    public final void setConsumerMenuViewModel(@NotNull ConsumerMenuViewModel var1) {
        Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
        this.consumerMenuViewModel = var1;
    }

    @NotNull
    public final View getMErrorLayout() {
        View var10000 = this.mErrorLayout;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mErrorLayout");
        }

        return var10000;
    }

    public final void setMErrorLayout(@NotNull View var1) {
        Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
        this.mErrorLayout = var1;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewDataBinding var10000 = DataBindingUtil.setContentView((Activity)this, -1300040);
        if (var10000 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.ipartha.t2s.databinding.ActivityMainBinding");
        } else {
            ActivityMainBinding binding = (ActivityMainBinding)var10000;
            this.initView(binding);
        }
    }

    private final void initView(ActivityMainBinding binding) {
        ConsumerMenuRepository consumerMenuRepository = new ConsumerMenuRepository((Context)this);
        ViewModel var10001 = ViewModelProviders.of((FragmentActivity)this, (Factory)(new ConsumerMenuViewModelFactory(consumerMenuRepository))).get(ConsumerMenuViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(var10001, "ViewModelProviders.of(th…enuViewModel::class.java)");
        this.consumerMenuViewModel = (ConsumerMenuViewModel)var10001;
        this.observeConsumerMenu(binding);
        ConsumerMenuViewModel var10000 = this.consumerMenuViewModel;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("consumerMenuViewModel");
        }

        var10000.fetchConsumerMenu();
        binding.setListener(this.retryClick);
        ErrorLayoutBinding var3 = binding.errorLayout;
        Intrinsics.checkExpressionValueIsNotNull(var3, "binding.errorLayout");
        View var4 = var3.getRoot();
        Intrinsics.checkExpressionValueIsNotNull(var4, "binding.errorLayout.root");
        this.mErrorLayout = var4;
    }

    private final void observeConsumerMenu(final ActivityMainBinding binding) {
        ConsumerMenuViewModel var10000 = this.consumerMenuViewModel;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("consumerMenuViewModel");
        }

        var10000.getConsumerMenu().observe((LifecycleOwner)this, (Observer)(new Observer() {
            // $FF: synthetic method
            // $FF: bridge method
            public void onChanged(Object var1) {
                this.onChanged((Result)var1);
            }

            public final void onChanged(Result result) {
                // $FF: Couldn't be decompiled
            }
        }));
    }

    public View _$_findCachedViewById(int var1) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }

        View var2 = (View)this._$_findViewCache.get(var1);
        if (var2 == null) {
            var2 = this.findViewById(var1);
            this._$_findViewCache.put(var1, var2);
        }

        return var2;
    }

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }

    }
}
