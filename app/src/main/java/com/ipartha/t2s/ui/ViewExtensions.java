package com.ipartha.t2s.ui;

import android.view.View;
import android.widget.ProgressBar;

import org.jetbrains.annotations.NotNull;

import kotlin.jvm.internal.Intrinsics;


public final class ViewExtensions {
    public static void hide(@NotNull ProgressBar $this$hide) {
        Intrinsics.checkParameterIsNotNull($this$hide, "$this$hide");
        $this$hide.setVisibility(View.GONE);
    }

    public static void show(@NotNull ProgressBar $this$show) {
        Intrinsics.checkParameterIsNotNull($this$show, "$this$show");
        $this$show.setVisibility(View.VISIBLE);
    }

    public static void show(@NotNull View $this$show) {
        Intrinsics.checkParameterIsNotNull($this$show, "$this$show");
        $this$show.setVisibility(View.VISIBLE);
    }

    public static void hide(@NotNull View $this$hide) {
        Intrinsics.checkParameterIsNotNull($this$hide, "$this$hide");
        $this$hide.setVisibility(View.GONE);
    }
}
