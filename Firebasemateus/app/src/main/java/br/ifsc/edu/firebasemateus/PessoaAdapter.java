package br.ifsc.edu.firebasemateus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class PessoaAdapter extends RecyclerView.Adapter<PessoaAdapter.MyViewHolder> {


    private Context myContext;
    private int myResource;
    ArrayList<Pessoa> dataSet;

    public PessoaAdapter(Context context, int resource, ArrayList<Pessoa> pessoa) {
        myContext = context;
        myResource = resource;
        dataSet = pessoa;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(myContext);
        View itemView = layoutInflater.inflate(myResource, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Pessoa pessoa = dataSet.get(position);
        holder.tvID.setText(pessoa.getId());
        holder.tvNome.setText(pessoa.getNome());
        holder.tvCpf.setText(pessoa.getCpf());
        holder.tvSexo.setText(pessoa.getSexo());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvID, tvNome, tvCpf, tvSexo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvID = itemView.findViewById(R.id.textViewId);
            tvNome = itemView.findViewById(R.id.textViewNome);
            tvCpf = itemView.findViewById(R.id.textViewCpf);
            tvSexo = itemView.findViewById(R.id.textViewSexo);

        }
    }
}
