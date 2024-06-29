package com.example.adicional;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProgressFragment extends Fragment {

    private ProgressCanvasView progressCanvasView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress, container, false);

        progressCanvasView = view.findViewById(R.id.progress_canvas);

        Button buttonRestart = view.findViewById(R.id.button_restart);
        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressCanvasView.resetProgress();
            }
        });

        Button buttonAdvance = view.findViewById(R.id.button_advance);
        buttonAdvance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressCanvasView.advanceProgress();
            }
        });

        return view;
    }
}