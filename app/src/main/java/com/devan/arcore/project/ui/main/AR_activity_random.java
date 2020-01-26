package com.devan.arcore.project.ui.main;

import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;

import com.devan.arcore.project.R;
import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AR_activity_random extends AppCompatActivity {
    private ModelRenderable Renderable;
    private ArFragment arCoreFragment;
    private String ar=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);

        switch (getIntent().getIntExtra("prandom",0)){
            case 0:
                ar= "mproduct_2.sfb";
                break;

        }

        arCoreFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.main_fragment);

       arCoreFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
           @Override
           public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
               Anchor anchor =hitResult.createAnchor();

               ModelRenderable.builder()
                       .setSource(AR_activity_random.this, Uri.parse(ar))
                       .build()
                       .thenAccept(modelRenderable -> addModelToScene(anchor,modelRenderable))
                       .exceptionally(throwable -> {
                           AlertDialog.Builder builder =new AlertDialog.Builder(AR_activity_random.this);
                           builder.setMessage(throwable.getMessage())
                                   .show();
                           return null;
                       });
           }
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
