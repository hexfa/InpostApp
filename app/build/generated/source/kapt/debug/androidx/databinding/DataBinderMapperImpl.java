package androidx.databinding;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new pl.inpost.recruitmenttask.DataBinderMapperImpl());
  }
}
