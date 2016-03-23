package udesk.udesksdk;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cn.udesk.UdeskSDKManager;

/**
 * Created by sks on 2016/2/4.
 */
public class UdeskCaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.udesk_use_case_activity_view);

        findViewById(R.id.btn_open_im).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UdeskSDKManager.getInstance().toLanuchChatAcitvity(UdeskCaseActivity.this);
            }
        });

        findViewById(R.id.acess_html).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UdeskSDKManager.getInstance().showRobot(UdeskCaseActivity.this);
            }
        });

        findViewById(R.id.acess_intelligent_selection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UdeskSDKManager.getInstance().showRobotOrConversation(UdeskCaseActivity.this);
            }
        });

        findViewById(R.id.btn_open_helper).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UdeskSDKManager.getInstance().toLanuchHelperAcitivty(UdeskCaseActivity.this);
            }
        });

        findViewById(R.id.im_agentgroup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UdeskSDKManager.getInstance().showConversationByImGroup(UdeskCaseActivity.this);
            }
        });

        findViewById(R.id.im_define_groupId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buildDialog("指定客服组 id 进行分配", "请输入客服组的ID", 1);
            }
        });
        findViewById(R.id.im_define_agentId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buildDialog("指定客服id 进行分配","请输入客服的ID",2);
            }
        });
    }

    private void toLanuchChatActivty(){

    }

    /**
     *
     * @param title
     * @param hint
     * @param flag  1 指定客服组id， 2 指定客服id
     */
    private void buildDialog(String title,String hint, final int flag ) {
        final CustomDialog   dialog = new CustomDialog(UdeskCaseActivity.this);
        dialog.setDialogTitle(title);
       final EditText editText = (EditText) dialog.getEditText();//方法在CustomDialog中实现
        editText.setHint(hint);
        dialog.setOnPositiveListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             dialog.dismiss();
                                             String input = editText.getText().toString();
                                             if (TextUtils.isEmpty(input.trim())) {
                                                 Toast.makeText(getApplicationContext(), "客服ID不能为空！", Toast.LENGTH_LONG).show();
                                             } else {
                                                 if (flag == 1) {
                                                     UdeskSDKManager.getInstance().lanuchChatByGroupId(UdeskCaseActivity.this, input.trim());
                                                 } else if (flag == 2) {
                                                     UdeskSDKManager.getInstance().lanuchChatByAgentId(UdeskCaseActivity.this, input.trim());
                                                 }
                                             }

                                         }
                                     }

        );
            dialog.setOnNegativeListener(new View.OnClickListener()

                                         {
                                             @Override
                                             public void onClick(View v) {
                                                 dialog.dismiss();
                                             }
                                         }

            );
            dialog.show();
        }


    }