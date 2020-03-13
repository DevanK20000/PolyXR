package com.devan.arcore.project.ui.main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
import com.devan.arcore.project.R;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class AR_activity extends AppCompatActivity {
    private ModelRenderable Renderable;
    private ArFragment arCoreFragment;
    private String ar=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);

        switch (getIntent().getIntExtra("raw",0)){
            case 1:
                ar="mproduct_1.sfb";
                break;
            case 2:
                ar="mproduct_2.sfb";

        }

        arCoreFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.main_fragment);

       arCoreFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
           Anchor anchor =hitResult.createAnchor();

           ModelRenderable.builder()
                   .setSource(AR_activity.this, Uri.parse(ar))
                   .build()
                   .thenAccept(modelRenderable -> addModelToScene(anchor,modelRenderable))
                   .exceptionally(throwable -> {
                       AlertDialog.Builder builder =new AlertDialog.Builder(AR_activity.this);
                       builder.setMessage(throwable.getMessage())
                               .show();
                       return null;
                   });
       });
    }

    private void addModelToScene(Anchor anchor, ModelRenderable modelRenderable) {
        AnchorNode anchorNode=new AnchorNode(anchor);
        TransformableNode transformableNode =new TransformableNode(arCoreFragment.getTransformationSystem());
        transformableNode.setParent(anchorNode);
        transformableNode.setRenderable(modelRenderable);
        arCoreFragment.getArSceneView().getScene().addChild(anchorNode);
        transformableNode.select();
    }
   
}
