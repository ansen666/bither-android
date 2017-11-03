package net.bither.bitherj.api;

import net.bither.bitherj.BitherjSettings;
import net.bither.bitherj.api.http.BitherUrl;
import net.bither.bitherj.api.http.HttpsGetResponse;
import net.bither.bitherj.utils.Utils;

/**
 * Created by zhangbo on 16/1/9.
 */
public class BlockExplorerApi extends HttpsGetResponse<String> {

    //总数
    public static BlockExplorerApi BlockCount(){
        String url="api/status?q=getBlockCount";
        return new BlockExplorerApi(url);
    }
    //根据blockheight取blockhash
    public static BlockExplorerApi BlockHash(int blockHeight){
        String url="api/block-index/"+blockHeight;
        return new BlockExplorerApi(url);
    }

    public static BlockExplorerApi SPVBlock(int lastBlockHeight){
        lastBlockHeight = 2016*(lastBlockHeight/2016);
        return BlockHash(lastBlockHeight);
    }

    //根据blockhash取block
    public static BlockExplorerApi Block(String hash){
        String url="api/block/"+hash;
        return new BlockExplorerApi(url);
    }

    //根据blockhash取block
    public static BlockExplorerApi AddressTxs(String address){
        String url="api/txs/?address="+address;
        return new BlockExplorerApi(url);
    }


    public static BlockExplorerApi RawTx(String hash){
        String url="api/rawtx/"+hash;
        return new BlockExplorerApi(url);
    }

    @Override
    public void setResult(String response) throws Exception {
        this.result = response;
    }

    public BlockExplorerApi(String url) {
        if(BitherjSettings.BITCOIN_TESTNET){
            url = "https://testnet.blockexplorer.com/"+url;
        }
        else{
            url = "https://blockexplorer.com/"+url;
        }
        setUrl(url);
    }

}
