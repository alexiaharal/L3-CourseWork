from django.conf.urls import patterns, url

from rango import views

urlpatterns = patterns('',
        url(r'^$', views.index, name='index'),
        url(r'^about.html', views.about, name='about'),
        url(r'^cat/(?P<category_name_url>\w+)', views.category, name='category'),
)
