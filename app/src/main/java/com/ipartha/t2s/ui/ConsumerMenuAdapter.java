package com.ipartha.t2s.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.ipartha.t2s.BR;
import com.ipartha.t2s.databinding.ConsumerMenuListBinding;
import com.ipartha.t2s.mvvm.ConsumerMenu;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

public final class ConsumerMenuAdapter extends RecyclerView.Adapter {
    private final List dataModelList;

    @NotNull
    public ConsumerMenuAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        ViewDataBinding var10000 = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), -1300041, parent, false);
        if (var10000 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.ipartha.t2s.databinding.ConsumerMenuListBinding");
        } else {
            ConsumerMenuListBinding binding = (ConsumerMenuListBinding)var10000;
            return new ConsumerMenuAdapter.ViewHolder(binding);
        }
    }

    // $FF: synthetic method
    // $FF: bridge method
    public androidx.recyclerview.widget.RecyclerView.ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
        return (androidx.recyclerview.widget.RecyclerView.ViewHolder)this.onCreateViewHolder(var1, var2);
    }

    public void onBindViewHolder(@NotNull ConsumerMenuAdapter.ViewHolder holder, int position) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        ConsumerMenu dataModel = (ConsumerMenu)this.dataModelList.get(position);
        holder.bind(dataModel);
    }

    // $FF: synthetic method
    // $FF: bridge method
    public void onBindViewHolder(androidx.recyclerview.widget.RecyclerView.ViewHolder var1, int var2) {
        this.onBindViewHolder((ConsumerMenuAdapter.ViewHolder)var1, var2);
    }

    public int getItemCount() {
        return this.dataModelList.size();
    }

    public ConsumerMenuAdapter(@NotNull List dataModelList) {
        super();
        Intrinsics.checkParameterIsNotNull(dataModelList, "dataModelList");
        this.dataModelList = dataModelList;
    }


    public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @NotNull
        private ConsumerMenuListBinding binding;

        public final void bind(@NotNull Object obj) {
            Intrinsics.checkParameterIsNotNull(obj, "obj");
            this.binding.setVariable(com.ipartha.t2s.BR.consumerMenu, obj);
            this.binding.executePendingBindings();
        }

        @NotNull
        public final ConsumerMenuListBinding getBinding() {
            return this.binding;
        }

        public final void setBinding(@NotNull ConsumerMenuListBinding var1) {
            Intrinsics.checkParameterIsNotNull(var1, "<set-?>");
            this.binding = var1;
        }

        public ViewHolder(@NotNull ConsumerMenuListBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkParameterIsNotNull(binding, "binding");
            this.binding = binding;
        }
    }
}

