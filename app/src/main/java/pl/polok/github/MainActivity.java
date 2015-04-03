/**
 * Copyright 2015 Marcin Polak
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.polok.github;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.github.polok.pincodepicker.PinCodeListener;
import com.github.polok.pincodepicker.PinCodeRecyclerView;
import com.github.polok.pincodepicker.PinCodeValidation;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PinCodeRecyclerView view = (PinCodeRecyclerView) findViewById(R.id.list);
        view.setPinCodeListener(new PinCodeListener() {
            @Override
            public void onPinCodeInserted(String pinCode) {
                Toast.makeText(MainActivity.this, "Whole code inserted", Toast.LENGTH_SHORT).show();
            }
        });

        view.setPinCodeValidation(new PinCodeValidation() {
            @Override
            public CharSequence getCorrectPinCode() {
                return "012345";
            }

            @Override
            public void onPinCodeCorrect(String pinCode) {
                Toast.makeText(MainActivity.this, "Code is correct", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPinCodeError(String pinCode) {
                Toast.makeText(MainActivity.this, "Code is NOT valid", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
