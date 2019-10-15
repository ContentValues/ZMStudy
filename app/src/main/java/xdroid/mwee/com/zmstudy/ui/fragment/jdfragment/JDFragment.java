package xdroid.mwee.com.zmstudy.ui.fragment.jdfragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.google.gson.Gson;

import xdroid.mwee.com.mwcommon.base.BaseFragment;
import xdroid.mwee.com.zmstudy.R;

/**
 * Author：created by SugarT
 * Time：2019/10/10 13
 */
public class JDFragment extends BaseFragment {


    private JDAdapter jdAdapter;

    public static JDFragment newInstance(){
        return new JDFragment();
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void initView(View v) {
        RecyclerView mMainRecyclerView = v.findViewById(R.id.mMainRecyclerView);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4, GridLayoutManager.VERTICAL, false);
//        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return 0;
//            }
//        });
//        mMainRecyclerView.setLayoutManager(gridLayoutManager);
        mMainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        jdAdapter = new JDAdapter(getContext());
        jdAdapter.setData(getData().itemInfoList);
        mMainRecyclerView.setAdapter(jdAdapter);
    }

    private HomeIndex getData() {

        String text = "{\n" +
                "    \"code\": \"0\",\n" +
                "    \"itemInfoList\": [\n" +
                "        {\n" +
                "            \"itemType\": \"topBanner\",\n" +
                "            \"module\": \"topBanner\",\n" +
                "            \"itemContentList\": [\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://m.360buyimg.com/mobilecms/s720x322_jfs/t4903/41/12296166/85214/15205dd6/58d92373N127109d8.jpg!q70.jpg\",\n" +
                "                    \"clickUrl\": \"男装超级品牌类日\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://img1.360buyimg.com/da/jfs/t4309/113/2596274814/85129/a59c5f5e/58d4762cN72d7dd75.jpg\",\n" +
                "                    \"clickUrl\": \"京东春茶季\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://m.360buyimg.com/mobilecms/s720x322_jfs/t4675/88/704144946/137165/bbbe8a4e/58d3a160N621fc59c.jpg!q70.jpg\",\n" +
                "                    \"clickUrl\": \"装出新高度\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://m.360buyimg.com/mobilecms/s720x322_jfs/t4627/177/812580410/198036/24a79c26/58d4f1e9N5b9fc5ee.jpg!q70.jpg\",\n" +
                "                    \"clickUrl\": \"宝宝出行利器\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://m.360buyimg.com/mobilecms/s720x322_jfs/t3097/241/9768114398/78418/47e4335e/58d8a637N6f178fbd.jpg!q70.jpg\",\n" +
                "                    \"clickUrl\": \"慕尼黑新品上市\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://m.360buyimg.com/mobilecms/s720x322_jfs/t4282/364/2687292678/87315/e4311cd0/58d4d923N24a2f5eb.jpg!q70.jpg\",\n" +
                "                    \"clickUrl\": \"空调让你百试不爽\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://img1.360buyimg.com/da/jfs/t4162/171/1874609280/92523/a1206b3f/58c7a832Nc8582e81.jpg\",\n" +
                "                    \"clickUrl\": \"奥妙全新上市\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://img1.360buyimg.com/da/jfs/t4387/338/1185667042/56822/bcd7fc3d/58d9e139Nadf09c53.jpg\",\n" +
                "                    \"clickUrl\": \"吸尘品类直降\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"itemType\": \"iconList\",\n" +
                "            \"module\": \"iconList\",\n" +
                "            \"itemContentList\": [\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://m.360buyimg.com/mobilecms/s80x80_jfs/t4477/120/1165061315/6371/82dfb453/58d9a09dNe4ba11e3.png\",\n" +
                "                    \"clickUrl\": \"京东超市\",\n" +
                "                    \"itemTitle\": \"京东超市\",\n" +
                "                    \"itemBackgroundImageUrl\": \"https://m.360buyimg.com/mobilecms/s720x286_jfs/t4789/121/1906619365/117219/4d1d6a36/58f5bd19N67369cca.jpg\",\n" +
                "                    \"itemSubTitle\": \"black\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://m.360buyimg.com/mobilecms/s80x80_jfs/t3286/167/1907269933/15789/da204cbe/57d53f16Nf3431cbd.png\",\n" +
                "                    \"clickUrl\": \"全球购\",\n" +
                "                    \"itemTitle\": \"全球购\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://m.360buyimg.com/mobilecms/s80x80_jfs/t3208/285/1806438443/12227/e35aa8d/57d5407cN0d6adf20.png\",\n" +
                "                    \"clickUrl\": \"服装城\",\n" +
                "                    \"itemTitle\": \"服装城\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://m.360buyimg.com/mobilecms/s80x80_jfs/t3079/222/1812395993/14681/29321e2c/57d54122N700d9c1b.png\",\n" +
                "                    \"clickUrl\": \"京东生鲜\",\n" +
                "                    \"itemTitle\": \"京东生鲜\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://m.360buyimg.com/mobilecms/s80x80_jfs/t3664/294/1570684882/19292/8b63dd4d/582adfbcNf82877de.png\",\n" +
                "                    \"clickUrl\": \"京东到家\",\n" +
                "                    \"itemTitle\": \"京东到家\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://m.360buyimg.com/mobilecms/s80x80_jfs/t3118/92/1836879034/12255/981e942a/57d54204N32b71c87.png\",\n" +
                "                    \"clickUrl\": \"充值中心\",\n" +
                "                    \"itemTitle\": \"充值中心\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://m.360buyimg.com/mobilecms/s80x80_jfs/t3424/278/301037516/11616/98748707/58096edbNcd05f66b.png\",\n" +
                "                    \"clickUrl\": \"惠赚钱\",\n" +
                "                    \"itemTitle\": \"惠赚钱\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://m.360buyimg.com/mobilecms/s80x80_jfs/t3202/244/9578875998/14975/afaba260/58d4ee32N29ed1055.png\",\n" +
                "                    \"clickUrl\": \"领券\",\n" +
                "                    \"itemTitle\": \"领券\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://m.360buyimg.com/mobilecms/s80x80_jfs/t3199/169/1818813995/12570/62402b0d/57d54364Needc47cd.png\",\n" +
                "                    \"clickUrl\": \"物流查询\",\n" +
                "                    \"itemTitle\": \"物流查询\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://m.360buyimg.com/mobilecms/s80x80_jfs/t3211/295/1824792746/12749/a74e2524/57d543ebN25337ef2.png\",\n" +
                "                    \"clickUrl\": \"我的关注\",\n" +
                "                    \"itemTitle\": \"我的关注\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "                     \"itemType\": \"showEvent\",\n" +
                "                     \"module\": \"showEvent\",\n" +
                "                     \"itemContentList\": [\n" +
                "                         {\n" +
                "                             \"imageUrl\": \"https://m.360buyimg.com/mobilecms/jfs/t5011/3/1981281525/91258/506b2dcf/58f5d98cN60b4bef7.jpg!q70.jpg\",\n" +
                "                             \"clickUrl\": \"劲省套餐\"\n" +
                "                         },\n" +
                "                         {\n" +
                "                             \"imageUrl\": \"https://m.360buyimg.com/mobilecms/jfs/t4444/174/3018675722/64709/6167dab0/58f5d9a5Nd354d25d.jpg!q70.jpg\",\n" +
                "                             \"clickUrl\": \"主会场\"\n" +
                "                         },\n" +
                "                         {\n" +
                "                             \"imageUrl\": \"https://m.360buyimg.com/mobilecms/jfs/t5068/79/1933755590/90462/a8b30cda/58f5d9c2N65a353e3.jpg!q70.jpg\",\n" +
                "                             \"clickUrl\": \"门店同款\"\n" +
                "                         }\n" +
                "                     ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"itemType\": \"newUser\",\n" +
                "            \"module\": \"newUser\",\n" +
                "            \"itemContentList\": [\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://m.360buyimg.com/mobilecms/jfs/t3229/183/8420242660/31884/d556597b/58c24709Nf44579e2.jpg!q70.jpg\",\n" +
                "                    \"clickUrl\": \"新用户专享\",\n" +
                "                    \"itemTitle\": \"京东超市\",\n" +
                "                    \"itemSubTitle\": \"\",\n" +
                "                    \"itemRecommendedLanguage\": \"\",\n" +
                "                    \"itemSubscript\": \"\",\n" +
                "                    \"itemBackgroundImageUrl\": \"\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://m.360buyimg.com/mobilecms/jfs/t3169/299/6474778567/28114/54ee4d66/58a6d4b3N69c6e565.jpg!q70.jpg\",\n" +
                "                    \"clickUrl\": \"新人红包\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://m.360buyimg.com/mobilecms/jfs/t4444/167/618381657/13640/b412722b/58d2a204N83cc6dbd.jpg!q70.jpg\",\n" +
                "                    \"clickUrl\": \"9.9包邮\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://m.360buyimg.com/mobilecms/jfs/t4303/36/2607357917/11737/a2500194/58d3ef41N8458849c.jpg!q70.jpg\",\n" +
                "                    \"clickUrl\": \"衣食住行\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://m.360buyimg.com/mobilecms/jfs/t3862/351/2286895626/41651/b9430342/58a6d991Ne9c82f29.jpg!q70.jpg\",\n" +
                "                    \"clickUrl\": \"首单满99新人礼包\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },{\n" +
                "            \"itemType\": \"jdBulletin\",\n" +
                "            \"module\": \"jdBulletin\",\n" +
                "            \"itemContentList\": [\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://m.360buyimg.com/mobilecms/s720x322_jfs/t4903/41/12296166/85214/15205dd6/58d92373N127109d8.jpg!q70.jpg\",\n" +
                "                    \"clickUrl\": \"男装超品类日，跨店3件7折！\",\n" +
                "                    \"itemTitle\": \"热\",\n" +
                "                    \"itemSubTitle\": \"男装超品类日，跨店3件7折！\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://img1.360buyimg.com/da/jfs/t4309/113/2596274814/85129/a59c5f5e/58d4762cN72d7dd75.jpg\",\n" +
                "                    \"clickUrl\": \"运动大牌春季特惠，满800减100！\",\n" +
                "                    \"itemTitle\": \"抢\",\n" +
                "                    \"itemSubTitle\": \"运动大牌春季特惠，满800减100！\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://img1.360buyimg.com/da/jfs/t4387/338/1185667042/56822/bcd7fc3d/58d9e139Nadf09c53.jpg\",\n" +
                "                    \"clickUrl\": \"美妆护肤，领券满199元减100！\",\n" +
                "                    \"itemTitle\": \"大促\",\n" +
                "                    \"itemSubTitle\": \"美妆护肤，领券满199元减100！\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"itemType\": \"jdSpikeHeader\",\n" +
                "            \"module\": \"jdSpike\",\n" +
                "            \"itemContentList\": [\n" +
                "                {\n" +
                "                    \"clickUrl\": \"秒杀专场\",\n" +
                "                    \"itemTitle\": \"16点场\",\n" +
                "                    \"itemSubTitle\": \"莫负春光进来逛逛\",\n" +
                "                    \"itemRecommendedLanguage\": \"500000\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"itemType\": \"jdSpikeContent\",\n" +
                "            \"module\": \"jdSpike\",\n" +
                "            \"itemContentList\": [\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://m.360buyimg.com/mobilecms/s220x220_jfs/t4186/44/1480543850/122209/11be676e/58c2676aN66a9c5ed.jpg!q70.jpg\",\n" +
                "                    \"clickUrl\": \"福临门\",\n" +
                "                    \"itemTitle\": \"73.9\",\n" +
                "                    \"itemSubTitle\": \"119.9\",\n" +
                "                    \"itemSubscript\": \"好货\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://m.360buyimg.com/mobilecms/s220x220_jfs/t799/288/51305470/57357/7bfb28a2/54f52c83Nb6c2cef5.jpg!q70.jpg\",\n" +
                "                    \"clickUrl\": \"刮胡刀\",\n" +
                "                    \"itemTitle\": \"299\",\n" +
                "                    \"itemSubTitle\": \"538\",\n" +
                "                    \"itemSubscript\": \"品质\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://m.360buyimg.com/mobilecms/s220x220_jfs/t2158/275/1115377524/647696/6c177b0/56792777N23d7cda0.png!q70.jpg\",\n" +
                "                    \"clickUrl\": \"ipad\",\n" +
                "                    \"itemTitle\": \"3199\",\n" +
                "                    \"itemSubTitle\": \"3688\",\n" +
                "                    \"itemSubscript\": \"经典\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://m.360buyimg.com/mobilecms/s220x220_jfs/t3790/31/64611920/414303/ae44859b/57fde07dN0c1bae84.jpg!q70.jpg\",\n" +
                "                    \"clickUrl\": \"美女玩具\",\n" +
                "                    \"itemTitle\": \"99\",\n" +
                "                    \"itemSubTitle\": \"158\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://m.360buyimg.com/mobilecms/s220x220_jfs/t3208/354/1454144017/140641/d876f6d2/57cd3936N647f9d2d.jpg!q70.jpg\",\n" +
                "                    \"clickUrl\": \"男裤\",\n" +
                "                    \"itemTitle\": \"199\",\n" +
                "                    \"itemSubTitle\": \"338\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://m.360buyimg.com/mobilecms/s220x220_jfs/t4582/132/1058213405/68736/4f05024/58d8595fN6d4f8092.jpg!q70.jpg\",\n" +
                "                    \"clickUrl\": \"自行车\",\n" +
                "                    \"itemTitle\": \"599\",\n" +
                "                    \"itemSubTitle\": \"1138\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://m.360buyimg.com/mobilecms/s220x220_jfs/t3847/3/2992821407/161733/b17f17ef/58748cb0N721e7a20.jpg!q70.jpg\",\n" +
                "                    \"clickUrl\": \"皮鞋\",\n" +
                "                    \"itemTitle\": \"159\",\n" +
                "                    \"itemSubTitle\": \"435\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"imageUrl\": \"https://m.360buyimg.com/mobilecms/s220x220_jfs/t2023/299/2364635879/258423/c2d89f21/56cfc313Nc798c4c9.jpg!q70.jpg\",\n" +
                "                    \"clickUrl\": \"减肥\",\n" +
                "                    \"itemTitle\": \"399\",\n" +
                "                    \"itemSubTitle\": \"638\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "           \"itemType\": \"type_Title\",\n" +
                "           \"module\": \"goShopping\",\n" +
                "           \"itemContentList\": [\n" +
                "               {\n" +
                "                   \"imageUrl\": \"https://m.360buyimg.com/mobilecms/jfs/t2677/290/3439129728/9956/4d607f09/578de9fcNb59b6153.png!q70.jpg\",\n" +
                "                   \"itemBackgroundImageUrl\": \"https://st.360buyimg.com/m/images/index/floor-tit.png\",\n" +
                "                   \"itemTitle\": \"更多热门\"\n" +
                "               }\n" +
                "           ]\n" +
                "       },\n" +
                "         {\n" +
                "             \"itemType\": \"type_22\",\n" +
                "             \"module\": \"goShopping\",\n" +
                "             \"itemContentList\": [\n" +
                "                 {\n" +
                "                     \"imageUrl\": \"https://m.360buyimg.com/n1/jfs/t3622/276/660901756/68531/e5d7241b/5813f6fbN03214d2d.jpg!q70.jpg\",\n" +
                "                     \"clickUrl\": \"大家电馆\",\n" +
                "                     \"itemTitle\": \"大家电馆\",\n" +
                "                     \"itemSubTitle\": \"白条6期免息\"\n" +
                "                 },\n" +
                "                 {\n" +
                "                     \"imageUrl\": \"https://m.360buyimg.com/mobilecms/jfs/t4666/290/1329628173/34033/a8e29b76/58dcdf92N5e3dff44.png!q70.jpg\",\n" +
                "                     \"clickUrl\": \"母婴馆\",\n" +
                "                     \"itemTitle\": \"母婴馆\",\n" +
                "                     \"itemSubTitle\": \"满199减100\",\n" +
                "                     \"itemRecommendedLanguage\": \"\"\n" +
                "                 }\n" +
                "             ]\n" +
                "         },\n" +
                "          {\n" +
                "              \"itemType\": \"type_22\",\n" +
                "              \"module\": \"goShopping\",\n" +
                "              \"itemContentList\": [\n" +
                "                  {\n" +
                "                      \"imageUrl\": \"https://m.360buyimg.com/mobilecms/jfs/t4246/177/3319375981/3828920/76ed6c38/58df9758Ndb750df0.jpg!q70.jpg\",\n" +
                "                      \"clickUrl\": \"时尚馆\",\n" +
                "                      \"itemTitle\": \"时尚馆\",\n" +
                "                      \"itemSubTitle\": \"京东丝袜节\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                      \"imageUrl\": \"https://m.360buyimg.com/mobilecms/jfs/t4378/235/3560807378/24770/8f222ffe/58e442e5Ndddccdae.jpg!q70.jpg\",\n" +
                "                      \"clickUrl\": \"手机数码\",\n" +
                "                      \"itemTitle\": \"手机数码\",\n" +
                "                      \"itemSubTitle\": \"联想爆品五折秒\",\n" +
                "                      \"itemRecommendedLanguage\": \"\"\n" +
                "                  }\n" +
                "              ]\n" +
                "          },\n" +
                "         {\n" +
                "             \"itemType\": \"type_1111\",\n" +
                "             \"module\": \"goShopping\",\n" +
                "             \"itemContentList\": [\n" +
                "                 {\n" +
                "                     \"imageUrl\": \"https://m.360buyimg.com/mobilecms/jfs/t4285/117/3109257512/19944/1be3b538/58db634bNcdb3ec1b.jpg!q70.jpg\",\n" +
                "                     \"clickUrl\": \"美食城\",\n" +
                "                     \"itemTitle\": \"美食城\",\n" +
                "                     \"itemSubTitle\": \"5件5折\",\n" +
                "                     \"itemRecommendedLanguage\": \"\",\n" +
                "                     \"itemBackgroundImageUrl\": \"\"\n" +
                "                 },\n" +
                "                 {\n" +
                "                     \"imageUrl\": \"https://m.360buyimg.com/mobilecms/jfs/t5068/345/197333997/10951/544031c8/58dca172N265a12e6.jpg!q70.jpg\",\n" +
                "                     \"clickUrl\": \"电脑办公\",\n" +
                "                     \"itemTitle\": \"电脑办公\",\n" +
                "                     \"itemRecommendedLanguage\": \"\",\n" +
                "                     \"itemSubTitle\": \"每满199减80\"\n" +
                "                 },\n" +
                "                 {\n" +
                "                     \"imageUrl\": \"https://m.360buyimg.com/mobilecms/jfs/t4228/306/3195162999/8876/88ab9216/58db69e3N0a944318.jpg!q70.jpg\",\n" +
                "                     \"clickUrl\": \"鞋靴箱包\",\n" +
                "                     \"itemTitle\": \"鞋靴箱包\",\n" +
                "                     \"itemSubTitle\": \"399减120\",\n" +
                "                     \"itemSubscript\": \"\"\n" +
                "                 },\n" +
                "                 {\n" +
                "                     \"imageUrl\": \"https://m.360buyimg.com/n1/jfs/t3088/300/7513997890/259901/98178f5/58b7c78cNbd0b5e05.jpg!q70.jpg\",\n" +
                "                     \"clickUrl\": \"小家电馆\",\n" +
                "                     \"itemTitle\": \"小家电馆\",\n" +
                "                     \"itemRecommendedLanguage\": \"\",\n" +
                "                     \"itemSubTitle\": \"每满199减20\",\n" +
                "                     \"itemSubscript\": \"\"\n" +
                "                 }\n" +
                "             ]\n" +
                "         }\n" +
                "    ]\n" +
                "}";

        Gson gson = new Gson();
        return gson.fromJson(text, HomeIndex.class);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(jdAdapter != null){
            jdAdapter.cancelAllTimers();
        }
    }
}
