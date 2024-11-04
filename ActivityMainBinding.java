// Generated by view binder compiler. Do not edit!
package app.altamira.com.minitwitter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import app.altamira.com.minitwitter.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonIngresarL;

  @NonNull
  public final EditText editTextPasswordL;

  @NonNull
  public final EditText editTextUsuarioL;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final TextView textViewRegistro;

  private ActivityMainBinding(@NonNull ConstraintLayout rootView, @NonNull Button buttonIngresarL,
      @NonNull EditText editTextPasswordL, @NonNull EditText editTextUsuarioL,
      @NonNull ImageView imageView, @NonNull TextView textViewRegistro) {
    this.rootView = rootView;
    this.buttonIngresarL = buttonIngresarL;
    this.editTextPasswordL = editTextPasswordL;
    this.editTextUsuarioL = editTextUsuarioL;
    this.imageView = imageView;
    this.textViewRegistro = textViewRegistro;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonIngresarL;
      Button buttonIngresarL = ViewBindings.findChildViewById(rootView, id);
      if (buttonIngresarL == null) {
        break missingId;
      }

      id = R.id.editTextPasswordL;
      EditText editTextPasswordL = ViewBindings.findChildViewById(rootView, id);
      if (editTextPasswordL == null) {
        break missingId;
      }

      id = R.id.editTextUsuarioL;
      EditText editTextUsuarioL = ViewBindings.findChildViewById(rootView, id);
      if (editTextUsuarioL == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.textViewRegistro;
      TextView textViewRegistro = ViewBindings.findChildViewById(rootView, id);
      if (textViewRegistro == null) {
        break missingId;
      }

      return new ActivityMainBinding((ConstraintLayout) rootView, buttonIngresarL,
          editTextPasswordL, editTextUsuarioL, imageView, textViewRegistro);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
